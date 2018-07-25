package com.enternityfintech.goldcard.business.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.ui.activity.AccountSafetyActivity;
import com.enternityfintech.goldcard.ui.activity.IdentityActivity;
import com.enternityfintech.goldcard.ui.activity.MessageActivity;
import com.enternityfintech.goldcard.ui.activity.MyProfileActivity;
import com.enternityfintech.goldcard.ui.activity.MyQrCodeActivity;
import com.enternityfintech.goldcard.ui.activity.SettingsActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cgy
 * 2018/6/14  17:39
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_portrait)
    CircleImageView ivPortrait;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_qr_Code)
    ImageView ivQrCode;
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.tv_auth)
    TextView tvAuth;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_settings)
    TextView tvSettings;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(args);
        return mineFragment;
    }


    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }




    @OnClick({R.id.iv_portrait, R.id.tv_notification, R.id.tv_auth, R.id.tv_account, R.id.tv_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_portrait:
                startActivity(new Intent(getActivity(), MyProfileActivity.class));
                break;
            case R.id.tv_notification:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.tv_auth:
                startActivity(new Intent(getActivity(), IdentityActivity.class));
                break;
            case R.id.tv_account:
                startActivity(new Intent(getActivity(), AccountSafetyActivity.class));
                break;
            case R.id.tv_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.iv_qr_Code:
                startActivity(new Intent(getActivity(),MyQrCodeActivity.class));
                break;
        }
    }
}
