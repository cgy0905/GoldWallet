package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.utils.LogUtils;
import com.enternityfintech.goldcard.utils.UIUtils;

/**
 * Created by cgy
 * 2018/6/26  10:06
 */
public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher{


    private int amount = 1; //要转的数量
    private int storage = 1; //持有数量



    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;
    public AmountView(Context context) {
        this(context,null);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.num_add_sub_view, this);
        etAmount = findViewById(R.id.etAmount);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease = findViewById(R.id.btnIncrease);

        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = ta.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = ta.getDimensionPixelSize(R.styleable.AmountView_tvWidth, UIUtils.dip2Px(60));
        int tvTextSize = ta.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = ta.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        ta.recycle();


        LayoutParams params = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(params);
        btnIncrease.setLayoutParams(params);


        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }

    }

    private OnAmountChangeListener listener;

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.listener = onAmountChangeListener;
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }




    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                LogUtils.d("onClick :  " + amount);
                etAmount.setText(amount + "");
            }
        } else if (v.getId() == R.id.btnIncrease) {
            if (amount < storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }
        //清除焦点
        etAmount.clearFocus();

        if (listener != null) {
            listener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > storage) {
            etAmount.setText(storage + "");
            return;
        }

        if (listener != null) {
            listener.onAmountChange(this, amount);
        }
    }
}
