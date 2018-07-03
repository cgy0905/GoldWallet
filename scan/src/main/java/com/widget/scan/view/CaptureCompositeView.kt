package com.widget.scan.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.RelativeLayout
import com.google.zxing.Result
import com.google.zxing.ResultPoint
import com.widget.permission.Permission
import com.widget.permission.PermissionHelper
import com.widget.scan.R
import com.widget.scan.camera.CameraManager
import com.widget.scan.camera.PreviewFrameShotListener
import com.widget.scan.camera.Size
import com.widget.scan.decode.*
import com.widget.scan.util.BeepManager
import com.widget.scan.util.DocumentUtil

class CaptureCompositeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), SurfaceHolder.Callback, PreviewFrameShotListener,
        DecodeResultListener, OnCheckedChangeListener, OnClickListener {

    private var previewSv: SurfaceView
    private var captureView: CaptureView
    private var flashCb: CheckBox
    private var albumBtn: Button
    private var beepManager: BeepManager? = null

    private var cameraManager: CameraManager? = null
    private var decodeThread: DecodeThread? = null
    private var previewFrameRect: Rect? = null
    private var isDecoding = false

    var onSucceed: ((bitmap: Bitmap, result: String) -> Unit)? = null
    var onError: ((reason: String, finishSelf: Boolean) -> Unit)? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_capture, this, true)
        previewSv = view.findViewById(R.id.sv_preview)
        captureView = view.findViewById(R.id.cv_capture)
        flashCb = view.findViewById(R.id.cb_capture_flash)
        flashCb.setOnCheckedChangeListener(this)
        flashCb.isEnabled = false
        albumBtn = view.findViewById(R.id.btn_album)
        albumBtn.setOnClickListener(this)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            albumBtn.visibility = View.GONE
        }
        previewSv.holder.addCallback(this)
        cameraManager = CameraManager(context)
        cameraManager!!.setPreviewFrameShotListener(this)
    }

    fun onResume() {
        if (beepManager == null) {
            beepManager = BeepManager(context as Activity)
        } else {
            beepManager!!.updatePrefs()
        }
    }

    fun onPause() {
        beepManager!!.close()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        PermissionHelper.Builder(context)
                .permission(Permission.Group.CAMERA)
                .permissionCallback {
                    cameraManager!!.initCamera(holder)
                    if (!cameraManager!!.isCameraAvailable) {
                        onError?.invoke(context.getString(R.string.capture_camera_failed), false)
                        return@permissionCallback
                    }
                    if (cameraManager!!.isFlashlightAvailable) {
                        flashCb.isEnabled = true
                    }
                    cameraManager!!.startPreview()
                    if (!isDecoding) {
                        cameraManager!!.requestPreviewFrameShot()
                    }
                }
                .build()
                .request()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        cameraManager!!.stopPreview()
        if (decodeThread != null) {
            decodeThread!!.cancel()
        }
        cameraManager!!.release()
    }

    override fun onPreviewFrame(data: ByteArray, dataSize: Size) {
        if (decodeThread != null) {
            decodeThread!!.cancel()
        }
        if (previewFrameRect == null) {
            previewFrameRect = cameraManager!!.getPreviewFrameRect(captureView.frameRect)
        }
        val luminanceSource = PlanarYUVLuminanceSource(data, dataSize,
                previewFrameRect!!)
        decodeThread = DecodeThread(luminanceSource, this@CaptureCompositeView)
        isDecoding = true
        decodeThread!!.execute()
    }

    override fun onDecodeSuccess(result: Result, source: LuminanceSource, bitmap: Bitmap) {
        var loacalBmp = bitmap
        beepManager!!.playBeepSoundAndVibrate()
        isDecoding = false
        if (loacalBmp.width > 100 || loacalBmp.height > 100) {
            val matrix = Matrix()
            matrix.postScale(100f / loacalBmp.width, 100f / loacalBmp.height)
            val resizeBmp = Bitmap.createBitmap(loacalBmp, 0, 0, loacalBmp.width,
                    loacalBmp.height, matrix, true)
            loacalBmp.recycle()
            loacalBmp = resizeBmp
        }

        onSucceed?.invoke(loacalBmp, result.text)
    }

    override fun onDecodeFailed(source: LuminanceSource) {
        if (source is RGBLuminanceSource) {
            onError?.invoke(context.getString(R.string.capture_decode_failed), true)
        }
        isDecoding = false
        cameraManager!!.requestPreviewFrameShot()
    }

    override fun foundPossibleResultPoint(point: ResultPoint) {
        captureView.addPossibleResultPoint(point)
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            cameraManager!!.enableFlashlight()
        } else {
            cameraManager!!.disableFlashlight()
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_album) {
            PermissionHelper.Builder(context)
                    .permission(Permission.Group.STORAGE)
                    .permissionCallback {
                        if (it) {
                            val intent: Intent = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                                Intent(Intent.ACTION_GET_CONTENT)
                            } else {
                                Intent(Intent.ACTION_OPEN_DOCUMENT)
                            }
                            intent.addCategory(Intent.CATEGORY_OPENABLE)
                            intent.type = "image/*"
                            intent.putExtra("return-data", true)
                            (context as Activity).startActivityForResult(intent, REQUEST_CODE_ALBUM)
                        } else {
                            onError?.invoke(context.getString(R.string.permission_failed),true)
                        }
                    }
                    .build()
                    .request()
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_ALBUM && resultCode == Activity.RESULT_OK && data != null) {
            val cameraBitmap: Bitmap?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val path = DocumentUtil.getPath(context, data.data)
                cameraBitmap = DocumentUtil.getBitmap(path)
            } else {
                // Not supported in SDK lower that KitKat
                return
            }
            if (cameraBitmap != null) {
                if (decodeThread != null) {
                    decodeThread!!.cancel()
                }
                val width = cameraBitmap.width
                val height = cameraBitmap.height
                val pixels = IntArray(width * height)
                cameraBitmap.getPixels(pixels, 0, width, 0, 0, width, height)
                val luminanceSource = RGBLuminanceSource(pixels, Size(width, height))
                decodeThread = DecodeThread(luminanceSource, this@CaptureCompositeView)
                isDecoding = true
                decodeThread!!.execute()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_ALBUM = 0
    }
}
