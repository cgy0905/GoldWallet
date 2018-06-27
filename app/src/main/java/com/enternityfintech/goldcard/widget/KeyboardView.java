package com.enternityfintech.gold.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.enternityfintech.gold.app.R;


/**
 */
public class KeyboardView extends FrameLayout implements View.OnClickListener {

    private CodeView codeView;
    private TextView textView;
    private Listener listener;
    private View submitView;

    private View hideView;

    public KeyboardView(Context context) {
        super(context);
        init();
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setCodeView(CodeView codeView) {
        this.codeView = codeView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setSubmitView(View submitView) {
        this.submitView = submitView;
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_pwd_keyboard, null);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                .MATCH_PARENT));
        hideView = view.findViewById(R.id.keyboard_hide);
        addView(view);
        view.findViewById(R.id.keyboard_0).setOnClickListener(this);
        view.findViewById(R.id.keyboard_1).setOnClickListener(this);
        view.findViewById(R.id.keyboard_2).setOnClickListener(this);
        view.findViewById(R.id.keyboard_3).setOnClickListener(this);
        view.findViewById(R.id.keyboard_4).setOnClickListener(this);
        view.findViewById(R.id.keyboard_5).setOnClickListener(this);
        view.findViewById(R.id.keyboard_6).setOnClickListener(this);
        view.findViewById(R.id.keyboard_7).setOnClickListener(this);
        view.findViewById(R.id.keyboard_8).setOnClickListener(this);
        view.findViewById(R.id.keyboard_9).setOnClickListener(this);
        view.findViewById(R.id.keyboard_delete).setOnClickListener(this);
        hideView.setOnClickListener(this);
    }

    public void setHideAble(Boolean hideAble) {
        hideView.setVisibility(hideAble ? View.VISIBLE : View.GONE);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        final String tag = (String) v.getTag();
        if (tag != null) {
            switch (tag) {
                case "hide":
                    hide();
                    break;
                case "delete":
                    if (codeView != null) {
                        codeView.delete();
                    }
                    if (listener != null) {
                        listener.onDelete();
                    }
                    if (textView != null) {
                        String text = textView.getText().toString();
                        if (text.length() > 0) {
                            text = text.substring(0, text.length() - 1);
                        }
                        textView.setText(text);
                    }
                    break;
                default:
                    if (codeView != null) {
                        codeView.input(tag);
                    }
                    if (listener != null) {
                        listener.onInput(tag);
                    }
                    if (textView != null) {
                        textView.append(tag);
                    }
                    break;
            }
        }
        if (submitView != null) {
            submitView.setEnabled(textView != null && textView.length() > 0);
        }
    }

    public interface Listener {

        public void onInput(String s);

        public void onDelete();

    }

}
