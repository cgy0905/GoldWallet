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

class CaptureComposite2 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr), SurfaceHolder.Callback, PreviewFrameShotListener, DecodeResultListener, OnCheckedChangeListener, OnClickListener {

    private val previewSv: SurfaceView
    private val captureView: CaptureView
    private val flashCb: CheckBox
    private val albumBtn: Button
    private var mBeepManager: BeepManager? = null

    private val mCameraManager: CameraManager
    private var mDecodeThread: DecodeThread? = null
    private var previewFrameRect: Rect? = null
    private var isDecoding = false

    var scanResultListener: ScanResultListener? = null

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
        mCameraManager = CameraManager(context)
        mCameraManager.setPreviewFrameShotListener(this)
    }

    fun onResume() {
        if (mBeepManager == null) {
            mBeepManager = BeepManager(context as Activity)
        } else {
            mBeepManager!!.updatePrefs()
        }
    }

    fun onPause() {
        mBeepManager!!.close()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mCameraManager.initCamera(holder)
        if (!mCameraManager.isCameraAvailable) {
            if (scanResultListener != null) {
                scanResultListener!!.onFailed(context.getString(R.string.capture_camera_failed), true)
            }
            return
        }
        if (mCameraManager.isFlashlightAvailable) {
            flashCb.isEnabled = true
        }
        mCameraManager.startPreview()
        if (!isDecoding) {
            mCameraManager.requestPreviewFrameShot()
        }
    }

    private fun checkPremission() {
        PermissionHelper.Builder(context)
                .permission(Permission.Group.CAMERA)
                .permissionCallback()
                .build()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mCameraManager.stopPreview()
        if (mDecodeThread != null) {
            mDecodeThread!!.cancel()
        }
        mCameraManager.release()
    }

    override fun onPreviewFrame(data: ByteArray, dataSize: Size) {
        if (mDecodeThread != null) {
            mDecodeThread!!.cancel()
        }
        if (previewFrameRect == null) {
            previewFrameRect = mCameraManager.getPreviewFrameRect(captureView.frameRect)
        }
        val luminanceSource = PlanarYUVLuminanceSource(data, dataSize,
                previewFrameRect!!)
        mDecodeThread = DecodeThread(luminanceSource, this@CaptureComposite2)
        isDecoding = true
        mDecodeThread!!.execute()
    }

    override fun onDecodeSuccess(result: Result, source: LuminanceSource, bitmap: Bitmap) {
        var bitmap = bitmap
        mBeepManager!!.playBeepSoundAndVibrate()
        isDecoding = false
        if (bitmap.width > 100 || bitmap.height > 100) {
            val matrix = Matrix()
            matrix.postScale(100f / bitmap.width, 100f / bitmap.height)
            val resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap
                    .height, matrix, true)
            bitmap.recycle()
            bitmap = resizeBmp
        }

        //TODO do something when scan succeed
        //  ScanResultActivity.launch(this, bitmap, result.getText());
        //        Intent resultData = new Intent();
        //        resultData.putExtra(EXTRA_RESULT, result.getText());
        //        resultData.putExtra(EXTRA_BITMAP, bitmap);
        //        setResult(RESULT_OK, resultData);
        //        finish();
    }

    override fun onDecodeFailed(source: LuminanceSource) {
        if (source is RGBLuminanceSource) {
            if (scanResultListener != null) {
                scanResultListener!!.onFailed(context.getString(R.string.capture_decode_failed), false)
            }
        }
        isDecoding = false
        mCameraManager.requestPreviewFrameShot()
    }

    override fun foundPossibleResultPoint(point: ResultPoint) {
        captureView.addPossibleResultPoint(point)
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            mCameraManager.enableFlashlight()
        } else {
            mCameraManager.disableFlashlight()
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_album) {
            val intent: Intent
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                intent = Intent(Intent.ACTION_GET_CONTENT)
            } else {
                intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            }
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            intent.putExtra("return-data", true)
            (context as Activity).startActivityForResult(intent, REQUEST_CODE_ALBUM)
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
                if (mDecodeThread != null) {
                    mDecodeThread!!.cancel()
                }
                val width = cameraBitmap.width
                val height = cameraBitmap.height
                val pixels = IntArray(width * height)
                cameraBitmap.getPixels(pixels, 0, width, 0, 0, width, height)
                val luminanceSource = RGBLuminanceSource(pixels, Size(width, height))
                mDecodeThread = DecodeThread(luminanceSource, this@CaptureComposite2)
                isDecoding = true
                mDecodeThread!!.execute()
            }
        }
    }

    companion object {

        private val VIBRATE_DURATION = 200L
        private val REQUEST_CODE_ALBUM = 0
    }
}
