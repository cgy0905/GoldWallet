package com.widget.scan.decode;

import android.graphics.Bitmap;

/**
 * Created by cool on 2018/7/3
 */
public interface ScanResultListener {
    void onSucceed(Bitmap bitmap,String result);
    void onFailed(String reason, boolean finishSelf);
}
