package com.enternityfintech.goldcard.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/29  16:17
 */
@SuppressLint("AppCompatCustomView")
public class PasswordInputView extends EditText {

    private int textLength;

    private float borderWidth;
    private float borderRadius;
    private int borderColor;

    private int passwordLength;
    private int passwordColor;
    private float passwordWidth;
    private float passwordRadius;

    private final int defaultSplitLineWidth  = 1;

    private Paint borderPaint;
    private Paint passwordPaint;



    public PasswordInputView(Context context) {
        this(context, null);
    }

    public PasswordInputView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int defaultBorderColor = getResources().getColor(R.color.board_gray);
        float defaultBorderWidth = getResources().getDimension(R.dimen.pwd_border_width);
        float defaultBorderRadius = getResources().getDimension(R.dimen.pwd_border_radius);

        int defaultPasswordLength = 6;
        int defaultPasswordColor = getResources().getColor(R.color.title);
        float defaultPasswordWidth = getResources().getDimension(R.dimen.pwd_width);
        float defaultPasswordRadius = getResources().getDimension(R.dimen.pwd_width);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PasswordInputView);

        try {
            borderColor = ta.getColor(R.styleable.PasswordInputView_borderColor, defaultBorderColor);
            borderWidth = ta.getDimension(R.styleable.PasswordInputView_borderWidth, defaultBorderWidth);
            borderRadius = ta.getDimension(R.styleable.PasswordInputView_borderRadius, defaultBorderRadius);
            passwordLength = ta.getInt(R.styleable.PasswordInputView_passwordLength, defaultPasswordLength);
            passwordColor = ta.getColor(R.styleable.PasswordInputView_passwordColor, defaultPasswordColor);
            passwordWidth = ta.getDimension(R.styleable.PasswordInputView_passwordWidth, defaultPasswordWidth);
            passwordRadius = ta.getDimension(R.styleable.PasswordInputView_passwordRadius, defaultPasswordRadius);
        } finally {
            ta.recycle();
        }
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setColor(borderColor);
        passwordPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        passwordPaint.setStrokeWidth(passwordWidth);
        passwordPaint.setStyle(Paint.Style.FILL);
        passwordPaint.setColor(passwordColor);

        setSingleLine(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();


        //画边框
        RectF rect = new RectF(0, 0, width, height);
        borderPaint.setColor(borderColor);
        canvas.drawRoundRect(rect, borderRadius, borderRadius, borderPaint);

        // 分割线
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(defaultSplitLineWidth);
        for (int i = 1; i < passwordLength; i++) {
            float x = width * i / passwordLength;
            canvas.drawLine(x, 0, x, height, borderPaint);
        }

        // 密码
        float cx, cy = height/ 2;
        float half = width / passwordLength / 2;
        for(int i = 0; i < textLength; i++) {
            cx = width * i / passwordLength + half;
            canvas.drawCircle(cx, cy, passwordWidth, passwordPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.textLength = text.toString().length();
        invalidate();
        if (textLength == passwordLength) {
            if (onCompleteListener != null) {
                onCompleteListener.onComplete();
            }
        }
    }

    private OnCompleteListener onCompleteListener;

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    public interface OnCompleteListener {
        void onComplete();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        borderPaint.setColor(borderColor);
        invalidate();
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        borderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
        invalidate();
    }

    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
        invalidate();
    }

    public int getPasswordColor() {
        return passwordColor;
    }

    public void setPasswordColor(int passwordColor) {
        this.passwordColor = passwordColor;
        passwordPaint.setColor(passwordColor);
        invalidate();
    }

    public float getPasswordWidth() {
        return passwordWidth;
    }

    public void setPasswordWidth(float passwordWidth) {
        this.passwordWidth = passwordWidth;
        passwordPaint.setStrokeWidth(passwordWidth);
        invalidate();
    }

    public float getPasswordRadius() {
        return passwordRadius;
    }

    public void setPasswordRadius(float passwordRadius) {
        this.passwordRadius = passwordRadius;
        invalidate();
    }

}
