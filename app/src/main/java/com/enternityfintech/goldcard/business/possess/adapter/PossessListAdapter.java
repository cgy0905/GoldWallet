package com.enternityfintech.goldcard.business.possess.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.business.possess.model.PossessBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cgy
 * 2018/7/4  15:38
 */
public class PossessListAdapter extends RecyclerView.Adapter<PossessListAdapter.ViewHolder> {

    private List<PossessBean> possessBeans = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_possess_standard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PossessBean possessBean = possessBeans.get(position);
        holder.ivPossess.setBackgroundResource(possessBean.getImageId());
        holder.tvCardCode.setText(possessBean.getCode());
        holder.tvBusiness.setText(possessBean.getBusiness());
        holder.tvVaultName.setText(possessBean.getVault());
    }

    @Override
    public int getItemCount() {
        return possessBeans != null ? possessBeans.size() : 0;
    }

    public void bind(List<PossessBean> possessBeans) {
        this.possessBeans = possessBeans;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_possess)
        ImageView ivPossess;
        @BindView(R.id.tv_card_code)
        TextView tvCardCode;
        @BindView(R.id.tv_business)
        TextView tvBusiness;
        @BindView(R.id.tv_vault_name)
        TextView tvVaultName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
