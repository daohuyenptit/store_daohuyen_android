package com.daohuyen.dell.store_cosmetics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.Transport;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransportAdapter extends RecyclerViewAdapter {
    public TransportAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_transport,parent,false);
        return new TransportViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Transport transport=this.getItem(position,Transport.class);
        TransportViewHolder viewHolder= (TransportViewHolder) holder;
        viewHolder.txt_name.setText(transport.getName());
        viewHolder.txt_price_trans.setText(Utils.formatNumberMoney(transport.getPrice())+" Đ");
        viewHolder.txt_time.setText(transport.getTime());

    }
    class  TransportViewHolder extends NormalViewHolder{

        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_price_trans)
        TextView txt_price_trans;
        @BindView(R.id.txt_time)
        TextView txt_time;

        public TransportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
