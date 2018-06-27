package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by cgy
 * 2018/6/26  11:07
 */
public class CouponView extends LinearLayout{


    private static final String TAG = "CouponView";

    //边缘小半圆的半径
    private float radius = 20;
    //左右边距
    private float paddingLeft;
    private float paddingRight;

    private Paint paint;
    private int defaultColor = Color.WHITE;

    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(defaultColor);


    }
}
