package com.enternityfintech.goldcard.business.login.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.business.login.presenter.LoginPresenter;
import com.enternityfintech.goldcard.business.login.iView.ILoginView;
import com.enternityfintech.goldcard.widget.ClearableEditText;
import com.enternityfintech.goldcard.widget.StatedImageView;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/20  14:10
 */
public class FastLoginFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {
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

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter((LoginActivity)getActivity());
    }

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

    @Override
    public void initListener() {
        etPhone.addTextChangedListener(watcher);
        etVerifyCode.addTextChangedListener(watcher);

        tvVerifyCode.setOnClickListener(v -> {
            if (tvVerifyCode.isEnabled()) {
                mPresenter.sendCode();
            }
        });
    }

    private boolean canLogin() {
        int codeLength = etVerifyCode.getText().toString().trim().length();
        int phoneLength = etPhone.getText().toString().trim().length();
        if (codeLength > 0 && phoneLength > 0) {
            return true;
        }
        return false;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_fast_login;
    }

    @Override
    public void loginSuccess() {

    }
}
