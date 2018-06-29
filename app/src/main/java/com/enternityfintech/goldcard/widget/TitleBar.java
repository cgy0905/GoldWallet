package com.enternityfintech.goldcard.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/20  9:18
 * 公共的标题栏
 */
public class TitleBar extends RelativeLayout {



    private Boolean isLeftBtnVisible;
    private int leftResId;

    private Boolean isLeftTvVisible;
    private String leftTvText;
    private int leftTvColor;

    private Boolean isRightBtnVisible;
    private int rightResId;

    private Boolean isRightTvVisible;
    private String rightTvText;
    private int rightTvColor;

    private Boolean isTitleVisible;
    private String titleText;
    private int titleColor;

    private int backgroundResId;

    private ImageView ivLeft, ivRight;
    private TextView tvTitle, tvLeftText, tvRightText;
    private View view;
    private RelativeLayout rlBg;

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

        if (attrs != null) {
            //读取attr属性
            final TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomToolBar, defStyleAttr, 0);

            /**-------------获取左边按钮属性-------------**/
            leftResId = ta.getResourceId(R.styleable.CustomToolBar_left_btn_src, -1);
            isLeftBtnVisible = ta.getBoolean(R.styleable.CustomToolBar_left_btn_visible, false);

            /**-------------获取左边文本属性------------*/
            isLeftTvVisible = ta.getBoolean(R.styleable.CustomToolBar_left_tv_visible, false);
            leftTvColor = ta.getColor(R.styleable.CustomToolBar_left_tv_color, Color.parseColor("#FFFFFF"));
            tvLeftText.setTextColor(leftTvColor);
            if (ta.hasValue(R.styleable.CustomToolBar_left_tv_text)) {
                leftTvText = ta.getString(R.styleable.CustomToolBar_left_tv_text);
            }
            /**-------------获取右边按钮属性------------*/
            isRightBtnVisible = ta.getBoolean(R.styleable.CustomToolBar_right_btn_visible, false);
            rightResId = ta.getResourceId(R.styleable.CustomToolBar_right_btn_src, -1);
            rightTvColor = ta.getColor(R.styleable.CustomToolBar_right_tv_color, Color.parseColor("#FFFFFF"));
            tvRightText.setTextColor(rightTvColor);
            /**-------------获取右边文本属性------------*/
            isRightTvVisible = ta.getBoolean(R.styleable.CustomToolBar_right_tv_visible, false);

            if (ta.hasValue(R.styleable.CustomToolBar_right_tv_text)) {
                rightTvText = ta.getString(R.styleable.CustomToolBar_right_tv_text);
            }

            /**-------------获取标题属性------------*/
            isTitleVisible = ta.getBoolean(R.styleable.CustomToolBar_title_visible, false);
            titleColor = ta.getColor(R.styleable.CustomToolBar_title_color, Color.parseColor("#333333"));
            tvTitle.setTextColor(titleColor);
            if(ta.hasValue(R.styleable.CustomToolBar_title_text)){
                titleText = ta.getString(R.styleable.CustomToolBar_title_text);
            }

            /**-------------背景颜色------------*/
            backgroundResId = ta.getResourceId(R.styleable.CustomToolBar_barBackground, -1);

            ta.recycle();

            if (isLeftBtnVisible) {
                ivLeft.setVisibility(VISIBLE);
            }
            if (isLeftTvVisible) {
                tvLeftText.setVisibility(VISIBLE);
            }
            if (isRightBtnVisible) {
                ivRight.setVisibility(VISIBLE);
            }

            if (isRightTvVisible) {
                tvRightText.setVisibility(VISIBLE);
            }

            if (isTitleVisible) {
                tvTitle.setVisibility(VISIBLE);
            }

            if (leftResId != -1) {
                ivLeft.setBackgroundResource(leftResId);
            }
            if (rightResId != -1) {
                ivRight.setBackgroundResource(rightResId);
            }
        }
    }

    private void initView() {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.include_titlebar, this, false);
            ivLeft = findViewById(R.id.iv_back);
            tvTitle = findViewById(R.id.tv_title);
            tvLeftText = findViewById(R.id.tv_left_text);
            ivRight = findViewById(R.id.iv_right);
            tvRightText = findViewById(R.id.tv_right_text);
            rlBg = findViewById(R.id.rl_bg);
            addView(view);
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
     * 设置左边按钮
     */
    public void setLeftBtnVisibility(boolean visible) {
        ivLeft.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /**
     * 右侧图标
     */
    public void setRightBtnVisibility(boolean visible) {
        ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /*
     * 点击事件
     */
    public void setOnLeftListener(OnClickListener listener) {
        ivLeft.setOnClickListener(listener);
    }

    public void setOnRightListener(OnClickListener listener) {
        ivRight.setOnClickListener(listener);
    }
}
