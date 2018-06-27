package com.enternityfintech.goldcard.ui.view;

import android.widget.EditText;

import com.enternityfintech.goldcard.widget.PaperButton;

/**
 * Created by cgy
 * 2018/6/19  11:22
 */
public interface IRegisterView{

    EditText getEtPhone();

    EditText getEtPwd();

    EditText getEtVerifyCode();

    PaperButton getBtnSendCode();

    PaperButton getBtnRegister();
}
