package com.enternityfintech.goldcard.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

import butterknife.BindView;

public class TradeDetailActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setAdapter(new TradeAdapter());
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_detail;
    }

    public class TradeAdapter extends RecyclerView.Adapter<TradeDetailViewHolder> {

        @NonNull
        @Override
        public TradeDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(TradeDetailActivity.this).inflate(R.layout
                    .holder_trade_detail, parent, false);
            return new TradeDetailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TradeDetailViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class TradeDetailViewHolder extends RecyclerView.ViewHolder {
        TradeDetailViewHolder(View itemView) {
            super(itemView);
        }
    }
}
