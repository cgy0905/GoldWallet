package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/20  9:18
 * 公共的标题栏
 */
public class ToolBar extends Toolbar{

    private ImageView ivBack, ivScan;
    private TextView tvTitle;
    public ToolBar(Context context) {
        this(context,null);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ToolBar, defStyleAttr, 0);

        int leftIvBackground = ta.getResourceId(R.styleable.ToolBar_leftBackground, 0);
        int rightIvBackground = ta.getResourceId(R.styleable.ToolBar_rightBackground, 0);
        String title = ta.getString(R.styleable.ToolBar_titleText);
        Boolean rightState = ta.getBoolean(R.styleable.ToolBar_rightState,true);
        float titleTextSize = ta.getDimension(R.styleable.ToolBar_titleTextSize, 0);
        int bgColor = ta.getColor(R.styleable.ToolBar_backgroundColor, Color.parseColor("#FFFFFF"));
        int titleTextColor = ta.getColor(R.styleable.ToolBar_titleTextColor, Color.parseColor("#333333"));
        ta.recycle();
        View.inflate(context, R.layout.include_titlebar, this);

        ivBack = findViewById(R.id.ivToolbarBack);
        tvTitle = findViewById(R.id.tvToolbarTitle);
        ivScan = findViewById(R.id.ivToolbarScan);
        tvTitle.setText(title);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setTextColor(titleTextColor);
        ivBack.setBackgroundResource(leftIvBackground);
        ivScan.setBackgroundResource(rightIvBackground);
        ivScan.setVisibility(rightState == true ? View.VISIBLE : View.INVISIBLE);
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
        ivScan.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    public void setOnRightListener(OnClickListener listener) {
        ivScan.setOnClickListener(listener);
    }
}
