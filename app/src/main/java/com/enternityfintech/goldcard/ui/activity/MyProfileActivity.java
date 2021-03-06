package com.enternityfintech.goldcard.ui.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.widget.imagepicker.ImagePicker;
import com.enternityfintech.goldcard.widget.TitleView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cgy
 * 2018/6/21  13:46
 */
public class MyProfileActivity extends BaseActivity {

    public static int REQ_UPDATE_HEAD_PORTRAIT = 1001;
    public static int REQ_MODIFY_GROUP_NAME = 1002;

    private ImagePicker imagePicker;

    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.iv_portrait)
    ImageView ivPortrait;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.rvNick)
    RelativeLayout rvNick;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.rvGender)
    RelativeLayout rvGender;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_profile;
    }

    @Override
    public void initView() {
        imagePicker = new ImagePicker(this);
        imagePicker.setOutput(300, 300);
    }



    private void modifySex() {
        final BottomSheetDialog sexDialog = new BottomSheetDialog(this, R.style.BottomDialog);
        sexDialog.setContentView(R.layout.dialog_sex);
        sexDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_GROUP_NAME) {
            if (resultCode == RESULT_OK && data != null) {
                //String nickName = data.getStringExtra("nickName");
                //更新用户信息

            }
        } else {
            imagePicker.onActivityResult(requestCode, resultCode, data);
        }
    }


    @OnClick({R.id.iv_portrait, R.id.rvNick, R.id.rvGender})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_portrait:
                rvNick.setOnClickListener(v -> {
                    Intent intent = new Intent(MyProfileActivity.this, ModifyNickActivity.class);
                    //intent.putExtra("nickName", tvNick.getText().toString());
                    startActivityForResult(intent, REQ_MODIFY_GROUP_NAME);
                });
                break;
            case R.id.rvNick:
                rvGender.setOnClickListener(v -> modifySex());
                break;
            case R.id.rvGender:
                ivPortrait.setOnClickListener(v -> imagePicker.showPath(REQ_UPDATE_HEAD_PORTRAIT));
                break;
        }
    }
}
