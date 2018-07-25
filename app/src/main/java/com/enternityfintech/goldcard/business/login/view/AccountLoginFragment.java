package com.enternityfintech.goldcard.business.login.view;

import android.view.View;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.widget.ClearableEditText;
import com.enternityfintech.goldcard.widget.StatedImageView;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by cgy
 * 2018/6/20  14:46
 */
public class AccountLoginFragment extends BaseFragment {

    @BindView(R.id.et_account)
    ClearableEditText etAccount;
    @BindView(R.id.et_password)
    ClearableEditText etPassword;
    @BindView(R.id.btn_login)
    StatedImageView btnLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    Unbinder unbinder;

    @Override
    protected void initView(View rootView) {


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_account_login;
    }


}
