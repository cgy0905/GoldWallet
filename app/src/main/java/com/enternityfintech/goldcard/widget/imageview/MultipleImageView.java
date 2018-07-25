package com.enternityfintech.goldcard.widget.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/7/6  9:31
 * <p>
 * Customized ImageView that implement the rounded corners ,borders and pressed color
 */
@SuppressLint("AppCompatCustomView")
public class MultipleImageView extends ImageView {

    private Paint pressPaint; //图片按下的画笔

    //图片的宽高
    private int width;
    private int height;

    //定义Bitmap的默认配置
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    //边框颜色
    private int borderColor;
    //边框宽度
    private int borderWidth;
    //按下的透明度
    private int pressAlpha;
    //按下的颜色
    private int pressColor;
    //圆角半径
    private int radius;
    //图片类型(矩形、圆形)
    private int shapeType;


    public MultipleImageView(Context context) {
        this(context, null);
    }

    public MultipleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MultipleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        //初始化默认值
        borderWidth = 6;
        borderColor = 0xddffffff;
        pressAlpha = 0x42;
        pressColor = 0x42000000;
        radius = 16;
        shapeType = 2;

        //获取控件的属性值
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MLImageView);
            borderColor = ta.getColor(R.styleable.MLImageView_ml_border_color, borderColor);
            borderWidth = ta.getDimensionPixelOffset(R.styleable.MLImageView_ml_border_width, borderWidth);
            pressAlpha = ta.getInteger(R.styleable.MLImageView_ml_press_alpha, pressAlpha);
            pressColor = ta.getColor(R.styleable.MLImageView_ml_press_color, pressColor);
            radius = ta.getDimensionPixelOffset(R.styleable.MLImageView_ml_radius, radius);
            shapeType = ta.getInteger(R.styleable.MLImageView_ml_shape_type, shapeType);
            ta.recycle();
        }

        //按下的画笔设置
        pressPaint = new Paint();
        pressPaint.setAntiAlias(true);
        pressPaint.setStyle(Paint.Style.FILL);
        pressPaint.setColor(pressColor);
        pressPaint.setAlpha(0);
        pressPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        setClickable(true);
        setDrawingCacheEnabled(true);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (shapeType == 0) {
            super.onDraw(canvas);
            return;
        }

        //获取当前控件的drawable
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        //这里get回来的宽度和高度是当前控件相对应的宽度和高度(在xml中设置)
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        //获取bitmap 即传入ImageView的bitmap
        //Bitmap bitmap = ((BitmapDrawable) ((SquaringDrawable)drawable).getCurrent()).getBitmap();

        Bitmap bitmap = getBitmapFromDrawable(drawable);
        drawDrawable(canvas, bitmap);

        drawPress(canvas);
        drawBorder(canvas);
    }

    private void drawDrawable(Canvas canvas, Bitmap bitmap) {
        //画笔
        Paint paint = new Paint();
        //颜色设置
        paint.setColor(0xffffffff);
        //抗锯齿
        paint.setAntiAlias(true);
        //Paint的Xfermode, PorterDuff.Mode.SER_IN 取两层图像的交集部分,只显示上层图像
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        //标志
        int saveFlags = Canvas.ALL_SAVE_FLAG;
        canvas.saveLayer(0, 0, width, height, null, saveFlags);

        if (shapeType == 1) {
            //画遮罩、画出来就是一个和控件大小相匹配的圆  （这里在半径上 -1 是为了不让图片超出边框）
            canvas.drawCircle(width / 2, height / 2, width / 2 - 1, paint);
        } else if (shapeType == 2) {
            //当shapeType = 2时 图片为圆角矩形 （这里在宽高上 -1 是为了不让图片超出边框）
            RectF rectF = new RectF(1, 1, getWidth() - 1, getHeight() - 1);
        }

        paint.setXfermode(xfermode);

        //空间的大小 /bitmap的大小 = bitmap 缩放的倍数
        float scaleWidth = ((float) getWidth()) / bitmap.getWidth();
        float scaleHeight = ((float) getHeight()) / bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //bitmap缩放
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        //画上去
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

    }

    /**
     * 绘制控件的按下效果
     *
     * @param canvas
     */
    private void drawPress(Canvas canvas) {
        //这里根据类型判断绘制的效果是圆形还是矩形
        if (shapeType == 1) {
            //当shapeType == 1时 图片为圆形(这里在半径上 -1 是为了不让图片超出边框)
            canvas.drawCircle(width / 2, height / 2, width / 2 - 1, pressPaint);
        } else if (shapeType == 2) {
            //当shapeType == 2时 图片为圆角矩形 （这里在宽高上 -1 是为了不让图片超出边框）
            RectF rectF = new RectF(1, 1, width - 1, height - 1);
            canvas.drawRoundRect(rectF, radius + 1, radius + 1, pressPaint);
        }
    }

    /**
     * 绘制自定义控件的边框
     *
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {
        if (borderWidth > 0) {
            Paint paint = new Paint();
            paint.setStrokeWidth(borderWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setAntiAlias(true);

            //根据控件类型的属性去绘制圆形或者矩形
            if (shapeType == 1) {
                canvas.drawCircle(width / 2, height / 2, (width - borderWidth) / 2, paint);
            } else if (shapeType == 2) {
                //当shapeType = 1时 图片为圆角矩形
                RectF rectF = new RectF(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth / 2, getHeight() - borderWidth / 2);
                canvas.drawRoundRect(rectF, radius, radius, paint);
            }
        }

    }

    /**
     * 重写父类的 onSizeChanged 方法 检测控件宽高的变化
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    /**
     * 重写 onTouchEvent 监听方法，用来监听自定义控件是否被触摸
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pressPaint.setAlpha(pressAlpha);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                pressPaint.setAlpha(0);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
                default:
                    pressPaint.setAlpha(0);
                    invalidate();
                    break;
        }
        return super.onTouchEvent(event);
    }

    //获取Bitmap的内容 glide加载图片导致drawable类型是属于SquaringDrawable类型 导致强转失败
    //这里通过drawable不同类型来获取bitmap
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        try {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            } else if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置边框颜色
     * @param borderColor
     */
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        invalidate();
    }

    /**
     * 设置边框宽度
     * @param borderWidth
     */
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * 设置图片按下颜色透明度
     * @param pressAlpha
     */
    public void setPressAlpha(int pressAlpha) {
        this.pressAlpha = pressAlpha;
    }

    /**
     * 设置图片按下的颜色
     * @param pressColor
     */
    public void setPressColor(int pressColor) {
        this.pressColor = pressColor;
    }

    /**
     * 设置倒角半径
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    /**
     * 设置形状类型
     * @param shapeType
     */
    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
        invalidate();
    }


}
