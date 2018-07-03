package com.enternityfintech.goldcard.business.login.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.business.login.contract.LoginContract;
import com.enternityfintech.goldcard.widget.ClearableEditText;
import com.enternityfintech.goldcard.widget.StatedImageView;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/20  14:10
 */
public class FastLoginFragment extends BaseFragment implements LoginContract.ILoginView {
    @BindView(R.id.et_phone)
    ClearableEditText etPhone;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_verify_code)
    TextView tvVerifyCode;
    @BindView(R.id.btn_login)
    StatedImageView btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;


    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnLogin.setEnabled(canLogin());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private boolean canLogin() {
        int codeLength = etVerifyCode.getText().toString().trim().length();
        int phoneLength = etPhone.getText().toString().trim().length();
        if (codeLength > 0 && phoneLength > 0) {
            return true;
        }
        return false;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_fast_login;
    }

    @Override
    public void loginSuccess() {

    }
}
