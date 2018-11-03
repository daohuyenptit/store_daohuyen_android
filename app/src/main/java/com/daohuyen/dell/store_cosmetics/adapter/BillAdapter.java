package com.daohuyen.dell.store_cosmetics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillAdapter extends RecyclerViewAdapter implements RecyclerViewAdapter.OnItemClickListener{
    public BillAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);

    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_bill,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Item item=getItem(position,Item.class);
        CartViewHolder cartViewHolder= (CartViewHolder) holder;
//        Glide.with(getContext())
//                .load(item.getProduct().getLogoUrl())
//                .apply(new RequestOptions().placeholder(R.drawable.logoapp)).into(cartViewHolder.img_product_detail);

            cartViewHolder.txt_stt.setText(position + 1 + "");
            cartViewHolder.tv_name_product.setText(item.getProduct().getName());
            cartViewHolder.tv_cost_product.setText(Utils.formatNumberMoney(item.getProduct().getPrice()) + "Đ");
            cartViewHolder.txt_number.setText(item.getNumber() + "");
            cartViewHolder.tv_total.setText(Utils.formatNumberMoney(item.getNumber() * item.getProduct().getPrice()) + "Đ");

    }





    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        if (isInSelectedMode() && viewType == VIEW_TYPE_NORMAL) {
            setSelectedItem(position, !isItemSelected(position));
        }

    }

    class  CartViewHolder extends NormalViewHolder{
        private ItemClickListener itemClickListener;
//        @BindView(R.id.img_product_detail)
//        ImageView img_product_detail;
        @BindView(R.id.txt_stt)
        TextView txt_stt;
        @BindView(R.id.txt_number)
        TextView txt_number;
        @BindView(R.id.tv_total)
        TextView tv_total;
        @BindView(R.id.tv_name_product)
        TextView tv_name_product;
        @BindView(R.id.tv_cost_product)
        TextView tv_cost_product;


        public CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
