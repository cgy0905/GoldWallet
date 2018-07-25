package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/26  11:07
 */
public class CouponView extends LinearLayout{


    private static final String TAG = "CouponView";

    //分割线颜色
    private int dividerLineColor;
    //两端半圆颜色
    private int portShapeColor;
    //两端半圆高度(直径)
    private int portShapeHeight;
    //两端半圆半径
    private int portShapeRadius;

    private Paint paint;


    //分割线默认颜色
    private final int LINE_DEFAULT_COLOR = 0xFFD9D9D9;
    //两端半圆默认颜色
    private final int SEMICIRCLE_DEFAULT_COLOR = 0XFFF2F2F2;
    //两端半圆默认高度(直径)
    private final int SEMICIRCLE_DEFAULT_HEIGHT = dp2px(15);
    private int width;
    private int height;

    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponView);
        dividerLineColor = ta.getColor(R.styleable.CouponView_divider_line_color, LINE_DEFAULT_COLOR);
        portShapeColor = ta.getColor(R.styleable.CouponView_port_shape_color, SEMICIRCLE_DEFAULT_COLOR);
        portShapeHeight = (int) ta.getDimension(R.styleable.CouponView_port_shape_height, SEMICIRCLE_DEFAULT_HEIGHT);
        portShapeRadius = portShapeHeight / 2;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(dp2px(1));
        ta.recycle();

    }

    //测量控件宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //请求父控件申请宽高
        setMeasuredDimension(width, height);
    }

    /**
     * 测量控件高度
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        int exactHeight = 0;

        if (mode == MeasureSpec.EXACTLY) {
            exactHeight = height;
        } else if (mode == MeasureSpec.AT_MOST) {
            exactHeight = getPaddingTop() + getPaddingBottom() + portShapeHeight;
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            exactHeight = getPaddingTop() + getPaddingBottom() + portShapeHeight;
        }
        return exactHeight;
    }



    //画半圆和虚线
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(portShapeColor);
        //画左端半圆 半圆是通过一个正方矩形构造出来的 画的时候需要画布移动半个矩形画出来才没有类型padding的空白
        RectF rect = new RectF(0, 0, portShapeHeight, portShapeHeight);
        canvas.translate( - portShapeRadius, 0);
        canvas.drawArc(rect, 270, 180, true, paint); //从270度开始画 画180度圆弧
        canvas.restore();

        //画虚线
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(dividerLineColor);
        DashPathEffect effect = new DashPathEffect(new float[]{5, 5}, 0);
        paint.setPathEffect(effect);
        Path path = new Path();
        path.moveTo(portShapeRadius + 10, getMeasuredHeight() / 2);
        path.lineTo(getMeasuredWidth() - portShapeRadius - 10, getMeasuredHeight() / 2);
        //设置路径效果后画line没有作用
//        canvas.drawLine(portShapeRadius + 10, getMeasuredHeight() / 2, getMeasuredWidth() - portShapeRadius - 10, getMeasuredHeight() / 2, paint);
        //虚线两端平移10个像素
        canvas.drawPath(path, paint);


        //画右端半圆, 半圆是通过一个正方矩形构造出来的，画的时候需要画布移动半个矩形画出来才没有类似padding的空白
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(portShapeColor);
        rect = new RectF(getMeasuredWidth() - portShapeHeight, 0 , getMeasuredWidth(), portShapeHeight);
        canvas.translate(portShapeRadius, 0);
        canvas.drawArc(rect, 90, 180,true, paint);//从90度开始画 画180度圆弧

    }


    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
