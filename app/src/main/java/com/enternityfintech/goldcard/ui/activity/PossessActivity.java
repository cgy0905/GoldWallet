package com.enternityfintech.goldcard.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.business.possess.adapter.PossessListAdapter;
import com.enternityfintech.goldcard.business.possess.model.PossessBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/25  14:18
 */
public class PossessActivity extends BaseActivity {
    private int[] images = {R.drawable.possess_one_hundred_bg,
            R.drawable.possess_one_hundred_bg,
            R.drawable.possess_five_hundred_bg,
            R.drawable.possess_two_hundred_bg,
            R.drawable.possess_one_hundred_bg,
            R.drawable.possess_ten_bg,
            R.drawable.possess_fifty_bg,
            R.drawable.possess_twenty_bg,
            R.drawable.possess_five_hundred_bg,
            R.drawable.possess_one_hundred_bg,
            R.drawable.possess_twenty_bg};

    private String[] codes = getResources().getStringArray(R.array.card_id);
    private String[] businesses = getResources().getStringArray(R.array.business_name);
    private String[] vaultNames = getResources().getStringArray(R.array.vault_name);

    private List<PossessBean> possessBeans;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private PossessListAdapter possessAdapter;

    @Override
    protected void initView() {
        possessBeans = new ArrayList<>();
        possessAdapter = new PossessListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(possessAdapter);

        for (int i = 0; i< images.length; i++) {
            possessBeans.add(new PossessBean(i, codes[i] , businesses[i], vaultNames[i]));
        }
        possessAdapter.bind(possessBeans);

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_possess;
    }

}
