package com.enternityfintech.goldcard.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/20  9:18
 * 公共的标题栏
 */
public class TitleBar extends Toolbar {

    private ImageView ivBack, ivMenu;
    private TextView tvTitle;
    private View view;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initView();

        setContentInsetsRelative(0,0);

        if (attrs != null) {
            //读取attr属性
            final TintTypedArray ta = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TitleBar, defStyleAttr, 0);

            Drawable leftIcon = ta.getDrawable(R.styleable.TitleBar_leftBackground);
            if (ivBack != null) {
                setLeftImageView(leftIcon);
            }
            Drawable rightIcon = ta.getDrawable(R.styleable.TitleBar_rightBackground);
            if (ivMenu != null) {
                setRightImageView(rightIcon);
            }
            String title = ta.getString(R.styleable.TitleBar_titleText);
            float titleTextSize = ta.getDimension(R.styleable.TitleBar_titleTextSize, 14);
            int titleTextColor = ta.getColor(R.styleable.TitleBar_titleTextColor, Color.parseColor("#333333"));
            if (tvTitle != null) {
                tvTitle.setText(title);
                tvTitle.setTextSize(titleTextSize);
                tvTitle.setTextColor(titleTextColor);
            }
            Boolean leftState = ta.getBoolean(R.styleable.TitleBar_leftState, false);
            Boolean rightState = ta.getBoolean(R.styleable.TitleBar_rightState, false);
            if (leftState) {
                //ivBack.setVisibility(leftState == true ? VISIBLE : INVISIBLE);
                showLeftImageView();
            }
            if (rightState) {
                //ivMenu.setVisibility(rightState == true ? View.VISIBLE : View.INVISIBLE);
                showRightImageView();
            }
            ta.recycle();
        }
    }

    private void showRightImageView() {
        if (ivMenu != null) {
            ivMenu.setVisibility(VISIBLE);
        }
    }

    public void showLeftImageView() {
        if (ivBack != null) {
            ivBack.setVisibility(VISIBLE);
        }
    }

    public void setRightImageView(Drawable rightIcon) {
        if (ivMenu != null) {
            ivMenu.setBackground(rightIcon);
        }
    }

    public void setLeftImageView(Drawable leftIcon) {
        if (ivBack != null) {
            ivBack.setBackground(leftIcon);
            ivBack.setVisibility(VISIBLE);
        }
    }
    public void setRightImageView(int resId) {
        setRightImageView(getResources().getDrawable(resId));
    }

    private void initView() {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.include_titlebar, null);
            ivBack = findViewById(R.id.iv_back);
            tvTitle = findViewById(R.id.tv_title);
            ivMenu = findViewById(R.id.iv_menu);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(view, params);
        }

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
        //view有id才会保存状态
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 管理返回按钮
     */
    public void setBackVisibility(boolean visible) {
        ivBack.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /**
     * 右侧图标
     */
    public void setRightIconVisibility(boolean visible) {
        ivMenu.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    public void setOnRightListener(OnClickListener listener) {
        ivMenu.setOnClickListener(listener);
    }
}
