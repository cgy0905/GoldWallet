package com.enternityfintech.goldcard.ui.activity;

import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.widget.PasswordInputView;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/22  10:07
 */
public class TradePwdVerifyActivity extends BaseActivity {
    @BindView(R.id.tv_verify_code_tip)
    TextView tvVerifyCodeTip;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_code)
    Button btnCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.layout_phone_verify)
    LinearLayout layoutPhoneVerify;
    @BindView(R.id.pwd_view)
    PasswordInputView pwdView;
    @BindView(R.id.layout_pwd_verify)
    LinearLayout layoutPwdVerify;



    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_pwd_verify;
    }


    @Override
    public void initView() {
        etCode.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
}
