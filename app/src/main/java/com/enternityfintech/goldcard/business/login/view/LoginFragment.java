package com.enternityfintech.goldcard.business.login.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.business.login.contract.LoginContract;
import com.enternityfintech.goldcard.widget.EditTextWithDel;
import com.enternityfintech.goldcard.widget.PaperButton;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/15  10:16
 */
public class LoginFragment extends BaseFragment implements LoginContract.ILoginView {


    @BindView(R.id.ivPhone)
    ImageView ivPhone;
    @BindView(R.id.et_phone)
    EditTextWithDel etPhone;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.ivPassword)
    ImageView ivPassword;
    @BindView(R.id.et_password)
    EditTextWithDel etPassword;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.btLogin)
    PaperButton btLogin;
    @BindView(R.id.tvForget)
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
    protected void initData() {

    }


    private void doLogin() {
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        //mPresenter.login(phone, password);
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
    protected int provideContentViewId() {
        return R.layout.fragment_login;
    }


    @Override
    public void loginSuccess() {

    }
}
