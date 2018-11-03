package com.daohuyen.dell.store_cosmetics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.model.view.LotproductView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOrderAdapter extends RecyclerViewAdapter {
    public DetailOrderAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_detailorder,parent,false);
        return new DetailOrderHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        LotproductView lotproductView=getItem(position,LotproductView.class);
        DetailOrderHolder detailOrderHolder= (DetailOrderHolder) holder;
        Glide.with(getContext())
                .load(lotproductView.getLogoUrl())
                .apply(new RequestOptions().placeholder(R.drawable.logoapp)).into(detailOrderHolder.img_product_detail);
        detailOrderHolder.tv_name_product.setText(lotproductView.getName());
        detailOrderHolder.tv_cost_product.setText(lotproductView.getPrice()+"Đ");
        detailOrderHolder.tv_number.setText(lotproductView.getAmount()+"");

    }
    class  DetailOrderHolder extends NormalViewHolder{
        @BindView(R.id.img_product_detail)
        ImageView img_product_detail;
        @BindView(R.id.tv_name_product)
        TextView tv_name_product;
        @BindView(R.id.tv_number)
        TextView tv_number;
        @BindView(R.id.tv_cost_product)
        TextView tv_cost_product;


        public DetailOrderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
