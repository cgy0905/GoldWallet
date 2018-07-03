package com.widget.scan.decode;

import android.graphics.Bitmap;

import com.google.zxing.Result;
import com.google.zxing.ResultPoint;

/**
 * Created by cool on 2018/7/3
 */
public interface DecodeResultListener {
    void onDecodeSuccess(Result result, LuminanceSource source, Bitmap bitmap);

    void onDecodeFailed(LuminanceSource source);

    void foundPossibleResultPoint(ResultPoint point);
}
