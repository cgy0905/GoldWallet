package com.enternityfintech.goldcard.widget;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.listener.OnImagePickListener;
import com.enternityfintech.goldcard.utils.FileUtils;
import com.enternityfintech.goldcard.utils.UIUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 * Created by jun on 2017/7/11.
 */

public class ImagePicker {
    private static final String IMAGE_FILE_LOCATION = Environment.getExternalStorageDirectory()
            .getPath() + "/temp.jpg";
    private Uri imageUri = Uri.fromFile(new File(IMAGE_FILE_LOCATION));

    private static final int PHOTO_LIBRARY = 402;
    private static final int TAKE_SMALL_PICTURE = 503;
    private static final int CROP_SMALL_PICTURE = 505;

    private static final int PERMISSION_CAMERA = 400;

    private Activity activity;
    private OnImagePickListener listener = null;
    private int mRequestCode = 100;

    private int aspectX = 1;
    private int aspectY = 1;
    private int outputX = 100;
    private int outputY = 100;

    private boolean shouldCut = true;
    private boolean mIsRequestPath = false;

    public ImagePicker(Activity activity) {
        super();
        this.activity = activity;
        File file = new File(IMAGE_FILE_LOCATION);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAspect(int aAspectX, int aAspectY) {
        aspectX = aAspectX;
        aspectY = aAspectY;
    }

    public void setPickerListener(OnImagePickListener aListener) {
        listener = aListener;
    }

    public void setOutput(int aOutputX, int aOutputY) {
        outputX = aOutputX;
        outputY = aOutputY;
    }

    public void show(int aRequestCode) {
        mRequestCode = aRequestCode;
        mIsRequestPath = false;
        showPick();
    }

    public void showPath(int aRequestCode) {
        mRequestCode = aRequestCode;
        mIsRequestPath = true;
        showPick();
    }

    //FIXME 拒绝权限后未走回调
    private void checkCamera() {
        AndPermission.with(activity)
                .permission(Permission.Group.CAMERA)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        activity.startActivityForResult(intent, TAKE_SMALL_PICTURE);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        UIUtils.showToast("拍照需要先开启相机权限");
                    }
                })
                .start();
    }
    //FIXME should be fixed, xiaomi7.1.1 can not pass
    private void checkAlbum(){
        AndPermission.with(activity)
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        startLibrary();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        UIUtils.showToast( "访问相册需要读写存储权限");
                    }
                })
                .start();
    }

    private void showPick() {
        final BottomSheetDialog dialog = new BottomSheetDialog(activity, R.style.BottomDialog);
        View dialogView = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_head_protrait, null);
        dialogView.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCamera();
                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.btn_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAlbum();
                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(dialogView);
        dialog.show();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        if (requestCode == PERMISSION_CAMERA) {
            int permission = grantResults[0];
            if (permission == PackageManager.PERMISSION_GRANTED) {
                showPick();
            } else if (permission == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(activity, "没有相机权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLibrary() {

        Intent intent = new Intent();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
            //            intent.setAction(Intent.ACTION_PICK);
        }
        intent.setType("image/*");
        activity.startActivityForResult(intent, PHOTO_LIBRARY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PHOTO_LIBRARY: {
                if (data != null) {
                    if (shouldCut) {
                        cropImageCarmera(data.getData());
                    } else {
                        Uri uri = data.getData();
                        if (uri != null) {
                            if (mIsRequestPath) {
                                String path = FileUtils.getImageAbsolutePath(activity, uri);
                                listener.onPickerResult(path, mRequestCode);
                            } else {
                                Bitmap bitmap = decodeUriAsBitmap(data.getData());
                                if (bitmap != null) {
                                    if (listener != null) {
                                        listener.onPickerResult(bitmap, mRequestCode);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
            case TAKE_SMALL_PICTURE: {
                if (shouldCut) {
                    cropImageCarmera(imageUri);
                } else {
                    if (imageUri != null) {
                        if (mIsRequestPath) {
                            String path = FileUtils.getImageAbsolutePath(activity, imageUri);
                            listener.onPickerResult(path, mRequestCode);
                        } else {
                            Bitmap bitmap = decodeUriAsBitmap(imageUri);
                            if (bitmap != null) {
                                if (listener != null) {
                                    listener.onPickerResult(bitmap, mRequestCode);
                                }
                            }
                        }
                    }
                }
            }
            break;
            case CROP_SMALL_PICTURE: {
                if (imageUri != null) {
                    if (mIsRequestPath) {
                        String path = FileUtils.getImageAbsolutePath(activity, imageUri);
                        listener.onPickerResult(path, mRequestCode);
                    } else {
                        Bitmap bitmap = decodeUriAsBitmap(imageUri);
                        if (bitmap != null) {
                            if (listener != null) {
                                listener.onPickerResult(bitmap, mRequestCode);
                            }
                        }
                    }
                }
            }
            break;
        }
    }

    private void cropImageCarmera(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            String pau = FileUtils.getImageAbsolutePath(activity, uri);
            intent.setDataAndType(Uri.fromFile(new File(pau)), "image/*");
        } else {
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        activity.startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            return null;
        }
        return bitmap;
    }

    public void setShouldCut(boolean shouldCut) {
        this.shouldCut = shouldCut;
    }


}
