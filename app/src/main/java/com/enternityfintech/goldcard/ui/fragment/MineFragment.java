package com.enternityfintech.goldcard.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.enternityfintech.goldcard.MainActivity;
import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.activity.AccountSafetyActivity;
import com.enternityfintech.goldcard.ui.activity.IdentityActivity;
import com.enternityfintech.goldcard.ui.activity.MessageActivity;
import com.enternityfintech.goldcard.ui.activity.MyProfileActivity;
import com.enternityfintech.goldcard.ui.activity.MyQrCodeActivity;
import com.enternityfintech.goldcard.ui.activity.SettingsActivity;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

import butterknife.BindView;
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
    TextView ivQrCode;
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
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initListener() {
        ivPortrait.setOnClickListener(v -> ((MainActivity)getActivity()).jumpToActivity(MyProfileActivity.class));
        tvNotification.setOnClickListener(v -> ((MainActivity)getActivity()).jumpToActivity(MessageActivity.class));
        tvAuth.setOnClickListener(v -> ((MainActivity)getActivity()).jumpToActivity(IdentityActivity.class));
        tvAccount.setOnClickListener(v -> ((MainActivity)getActivity()).jumpToActivity(AccountSafetyActivity.class));
        tvSettings.setOnClickListener(v -> ((MainActivity)getActivity()).jumpToActivity(SettingsActivity.class));
        ivQrCode.setOnClickListener(v -> ((MainActivity)getActivity()).jumpToActivity(MyQrCodeActivity.class));
    }
}
