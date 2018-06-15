package com.enternityfintech.goldcard.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import com.enternityfintech.goldcard.R;

/**
 * Created by cgy
 * 2018/6/15  11:02
 */
public class EditTextWithDel extends EditText{

    private static final String TAG = "EditTextWithDel";

    private Drawable imgDrawable;

    private Context mContext;


    public EditTextWithDel(Context context) {
        this(context, null);
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        imgDrawable = mContext.getResources().getDrawable(R.drawable.close_pushcha);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setDrawable();
    }

    private void setDrawable() {
        if (length() < 1)
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        else
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgDrawable, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }



        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
