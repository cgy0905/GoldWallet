package com.enternityfintech.goldcard.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.activity.LoginActivity;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.presenter.LoginPresenter;
import com.enternityfintech.goldcard.ui.view.ILoginView;
import com.enternityfintech.goldcard.widget.EditTextWithDel;
import com.enternityfintech.goldcard.widget.PaperButton;

import butterknife.Bind;

/**
 * Created by cgy
 * 2018/6/15  10:16
 */
public class LoginFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {


    @Bind(R.id.ivPhone)
    ImageView ivPhone;
    @Bind(R.id.et_phone)
    EditTextWithDel etPhone;
    @Bind(R.id.rl_name)
    RelativeLayout rlName;
    @Bind(R.id.ivPassword)
    ImageView ivPassword;
    @Bind(R.id.et_password)
    EditTextWithDel etPassword;
    @Bind(R.id.rl_password)
    RelativeLayout rlPassword;
    @Bind(R.id.btLogin)
    PaperButton btLogin;
    @Bind(R.id.tvForget)
    TextView tvForget;


    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btLogin.setEnabled(canLogin());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initListener() {
        etPhone.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);
        btLogin.setOnClickListener(v -> doLogin());
    }

    private void doLogin() {
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        mPresenter.login(phone, password);
    }

    private boolean canLogin() {
        int pwdLength = etPassword.getText().toString().trim().length();
        int phoneLength = etPhone.getText().toString().trim().length();
        if (pwdLength > 0 && phoneLength > 0) {
            return true;
        }
        return false;
    }


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter((LoginActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showUserNameError(String error) {

    }

    @Override
    public void showPassWordError(String error) {

    }

    @Override
    public void loginSuccess() {

    }

}
