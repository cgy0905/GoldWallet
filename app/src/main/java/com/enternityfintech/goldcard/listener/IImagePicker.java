package com.enternityfintech.goldcard.listener;

import android.graphics.Bitmap;

/**
 * Created by jun on 2017/7/11.
 */

public interface IImagePicker {
    public void onPickerResult(Bitmap bitmap, int requestCode);

    public void onPickerResult(String path, int requestCode);
}
