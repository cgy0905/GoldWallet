package com.enternityfintech.goldcard.business.login.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.business.login.contract.RegisterContract;
import com.enternityfintech.goldcard.business.login.presenter.RegisterPresenter;
import com.enternityfintech.goldcard.widget.EditTextWithDel;
import com.enternityfintech.goldcard.widget.PaperButton;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/15  10:16
 */
public class RegisterFragment extends BaseFragment implements RegisterContract.IRegisterView {
    @BindView(R.id.ivPhone)
    ImageView ivPhone;
    @BindView(R.id.etPhone)
    EditTextWithDel etPhone;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.etVerifyCode)
    EditTextWithDel etVerifyCode;
    @BindView(R.id.rlVerifyCode)
    RelativeLayout rlVerifyCode;
    @BindView(R.id.btnSend)
    PaperButton btnSend;
    @BindView(R.id.ivPassword)
    ImageView ivPassword;
    @BindView(R.id.etPassword)
    EditTextWithDel etPassword;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.btnRegister)
    PaperButton btnRegister;
    @BindView(R.id.llRegister)
    LinearLayout llRegister;



    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_register;
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnRegister.setEnabled(canRegister());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void initListener() {
        etPhone.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);
        etVerifyCode.addTextChangedListener(watcher);


        btnSend.setOnClickListener(v -> {
            if (btnSend.isEnabled()) {
                mPresenter.sendCode();
            }
        });

        btnRegister.setOnClickListener(v -> mPresenter.register());

    }



    private boolean canRegister() {
        int phoneLength = etPhone.getText().toString().trim().length();
        int pwdLength = etPassword.getText().toString().trim().length();
        int codeLength = etVerifyCode.getText().toString().trim().length();
        if (phoneLength > 0 && pwdLength > 0 && codeLength > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void registerSuccess() {

    }
}
