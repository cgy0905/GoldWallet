package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/26  10:06
 */
public class AmountView extends LinearLayout implements View.OnClickListener {


    private int amount = 1; //要转的数量
    private int maxAmount = 10; //持有数量

    private TextView amoutTv;
    private StatedImageView subIv;
    private StatedImageView addIv;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.num_add_sub_view, this);
        amoutTv = findViewById(R.id.tv_amount);
        subIv = findViewById(R.id.iv_sub);
        addIv = findViewById(R.id.iv_add);

        subIv.setOnClickListener(this);
        addIv.setOnClickListener(this);
        subIv.setEnabled(false);
    }

    private OnAmountChangeListener listener;

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.listener = onAmountChangeListener;
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
        if (maxAmount == 0) {
            subIv.setEnabled(false);
            addIv.setEnabled(false);
        }
    }

    public int getSelectedCount() {
        return amount;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_sub) {
            if (--amount == 0) {
                subIv.setEnabled(false);
            }
            addIv.setEnabled(true);
            amoutTv.setText(String.valueOf(amount));
        } else if (v.getId() == R.id.iv_add) {
            if (++amount == maxAmount) {
                addIv.setEnabled(false);
            }
            subIv.setEnabled(true);
            amoutTv.setText(String.valueOf(amount));
        }

        if (listener != null) {
            listener.onAmountChange(this, amount);
        }
    }
}
