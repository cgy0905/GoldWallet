package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.utils.UIUtils;

/**
 * Created by cgy
 * 2018/7/5  15:22
 */
public class CouponView2 extends LinearLayout{

    private int width;
    private int height;

    private int radius;

    private Paint paint;


    //两端半圆默认高度(直径)
    private final int DEFAULT_RADIUS = UIUtils.dip2Px(15);

    public CouponView2(Context context) {
        this(context, null);
    }

    public CouponView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CouponView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponView2);
        radius = ta.getDimensionPixelSize(R.styleable.CouponView2_radius, DEFAULT_RADIUS);

        ta.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(0, height, radius, paint);
        canvas.drawCircle(width, height, radius, paint);
    }
}
