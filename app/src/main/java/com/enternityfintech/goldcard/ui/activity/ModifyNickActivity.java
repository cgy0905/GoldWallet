package com.enternityfintech.goldcard.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.widget.FilterEditText;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/21  14:44
 */
public class ModifyNickActivity extends BaseActivity {


    private String nickName;

    @BindView(R.id.et_nick)
    FilterEditText etNick;

    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    public void init() {
//        nickName = getIntent().getStringExtra("nickName");
//        etNick.setText(nickName);
//        etNick.setSelection(nickName.length());
    }

    @Override
    protected void initView() {
        btnSave.setOnClickListener(v -> {
            String nickName = etNick.getText().toString().trim();
            if (TextUtils.isEmpty(nickName)) {
                return;
            }
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
        });
    }



    @Override
    protected int provideContentViewId() {
        return R.layout.activity_modify_nick;
    }


}
