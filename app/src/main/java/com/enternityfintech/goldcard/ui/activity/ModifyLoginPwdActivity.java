package com.enternityfintech.goldcard.ui.activity;

import android.widget.Button;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.widget.ClearableEditText;
import com.enternityfintech.goldcard.widget.StatedImageView;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/21  16:12
 */
public class ModifyLoginPwdActivity extends BaseActivity {
    @BindView(R.id.et_old_pwd)
    ClearableEditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    ClearableEditText etNewPwd;
    @BindView(R.id.et_sure_pwd)
    ClearableEditText etSurePwd;
    @BindView(R.id.btn_submit)
    StatedImageView btnSubmit;
    @BindView(R.id.btn_forget)
    Button btnForget;



    @Override
    protected void initView() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_modify_login_pwd;
    }



}
