package com.enternityfintech.goldcard.ui.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;
import com.enternityfintech.goldcard.widget.ImagePicker;
import com.enternityfintech.goldcard.widget.TitleView;

import butterknife.BindView;

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
    @BindView(R.id.iv_head)
    ImageView ivHead;
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
    @BindView(R.id.iv_portrait)
    ImageView ivPortrait;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_profile;
    }

    @Override
    public void initListener() {
        rvNick.setOnClickListener(v -> {
            Intent intent = new Intent(MyProfileActivity.this, ModifyNickActivity.class);
            intent.putExtra("nickName", tvNick.getText().toString());
            startActivityForResult(intent, REQ_MODIFY_GROUP_NAME);
        });
        rvGender.setOnClickListener(v -> modifySex());

        ivPortrait.setOnClickListener(v -> imagePicker.showPath(REQ_UPDATE_HEAD_PORTRAIT));
    }


    private void modifySex() {
        final BottomSheetDialog sexDialog = new BottomSheetDialog(this,R.style.BottomDialog);
        sexDialog.setContentView(R.layout.dialog_sex);
        sexDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_GROUP_NAME) {
            if (resultCode == RESULT_OK && data != null) {
                String nickName = data.getStringExtra("nickName");
                //更新用户信息

            }
        } else {
            imagePicker.onActivityResult(requestCode, resultCode, data);
        }
    }
}
