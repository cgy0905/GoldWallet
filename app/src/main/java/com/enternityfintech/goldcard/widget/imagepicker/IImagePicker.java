package com.enternityfintech.goldcard.widget.imagepicker;

import android.graphics.Bitmap;

/**
 * Created by jun on 2017/7/11.
 */

public interface IImagePicker {

    void onPickerResult(Bitmap bitmap, int requestCode);

    void onPickerResult(String path, int requestCode);
}
