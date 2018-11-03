package com.daohuyen.dell.store_cosmetics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.model.Pay;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayAdapter extends RecyclerViewAdapter {
    public PayAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_pay,parent,false);
        return new PayViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Pay pay=this.getItem(position,Pay.class);
        PayViewHolder viewHolder= (PayViewHolder) holder;
        viewHolder.imageView.setImageResource(pay.getImage());
        viewHolder.txt_pay.setText(pay.getName());

    }
    class PayViewHolder extends NormalViewHolder {
        @BindView(R.id.img_pay)
        ImageView imageView;
        @BindView(R.id.txt_pay)
        TextView txt_pay;



        public PayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
