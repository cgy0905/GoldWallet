package com.enternityfintech.goldcard.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * fixme refractory it with mvp
 */
public class TradeRecordActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TradeRecordAdapter(mockData()));
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_record;
    }

    private List<Data> mockData() {
        ArrayList<Data> mock = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(5) + 3;
        int item = random.nextInt(3) + 3;
        int[] icons = new int[]{R.drawable.ic_maijin, R.drawable.ic_sell,
                R.drawable.ic_shoujin, R.drawable.ic_zhuanjin};
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < size; i++) {
            Data data = new Data();
            data.month = calendar.getTime().toString();
            ArrayList<Data.Item> items = new ArrayList<>();
            for (int j = 0; j < item; j++) {
                Data.Item di = new Data.Item();
                di.date = calendar.getTime().toString();
                di.icon = icons[random.nextInt(icons.length)];
                di.title = "类型";
                di.weight = "+" + random.nextInt(1000) + "克";
                if (random.nextBoolean()) {
                    di.color = ContextCompat.getColor(this, R.color.main_color);
                } else {
                    di.color = ContextCompat.getColor(this, R.color.title);
                }

                items.add(di);
            }
            data.items = items;
            mock.add(data);
        }
        return mock;
    }

    //mock
    public static class Data {
        String month;
        List<Item> items;

        public static class Item {
            int icon;
            String title;
            String date;
            String weight;
            int color;
        }
    }

    public class TradeRecordAdapter extends RecyclerView.Adapter<TradeRecordViewHolder> {

        private List<Data> datas;

        public TradeRecordAdapter(List<Data> datas) {
            this.datas = datas;
        }

        @NonNull
        @Override
        public TradeRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TradeRecordViewHolder(LayoutInflater.from(TradeRecordActivity.this)
                    .inflate(R.layout.holder_trade_record, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TradeRecordViewHolder holder, int position) {
            Data data = datas.get(position);
            holder.monthTv.setText(data.month);
            holder.container.removeAllViews();
            for (Data.Item item : data.items) {
                View view = LayoutInflater.from(TradeRecordActivity.this).inflate(R.layout
                        .holder_trade_record_item, holder.container, false);
                ImageView iconIv = view.findViewById(R.id.iv_icon);
                iconIv.setImageResource(item.icon);
                TextView titleTv = view.findViewById(R.id.tv_title);
                titleTv.setText(item.title);
                TextView timeTv = view.findViewById(R.id.tv_time);
                timeTv.setText(item.date);
                TextView weightTv = view.findViewById(R.id.tv_weight);
                weightTv.setText(item.weight);
                weightTv.setTextColor(item.color);
                holder.container.addView(view);
            }
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    class TradeRecordViewHolder extends RecyclerView.ViewHolder {
        TextView monthTv;
        LinearLayout container;

        TradeRecordViewHolder(View itemView) {
            super(itemView);
            monthTv = itemView.findViewById(R.id.tv_month);
            container = itemView.findViewById(R.id.container);
        }
    }
}
