package com.daohuyen.dell.store_cosmetics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.daohuyen.dell.store_cosmetics.R.color.light_gray;

public class CartAdapter extends RecyclerViewAdapter implements RecyclerViewAdapter.OnItemClickListener,RecyclerViewAdapter.OnItemSelectionChangedListener{
    private ClickChangeCount clickChangeCount;
    public CartAdapter(Context context, ClickChangeCount clickChangeCount) {
        super(context, false);
        setOnItemSelectionChangeListener(this);
        this.clickChangeCount=clickChangeCount;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.order_detail,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {

        Item item=getItem(position,Item.class);
        CartViewHolder cartViewHolder= (CartViewHolder) holder;
        Glide.with(getContext())
                .load(item.getProduct().getLogoUrl())
                .apply(new RequestOptions().placeholder(R.drawable.logoapp)).into(cartViewHolder.img_product_detail);
        cartViewHolder.tv_name_product.setText(item.getProduct().getName());
        cartViewHolder.tv_cost_product.setText(Utils.formatNumberMoney(item.getProduct().getPrice())+"Đ");
        cartViewHolder.txt_number.setText(item.getNumber()+"");

        cartViewHolder.img_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getNumber()>1) {
                    clickChangeCount.clickSubCount(position);
                }
            }
        });
        cartViewHolder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChangeCount.clickAddCount(position);
            }
        });

    }

    @Override
    public void setSelectedMode(boolean isSelected) {
        super.setSelectedMode(isSelected);
        if (isSelected) {
            backup();
            addOnItemClickListener(this);
            notifyItemRangeChanged(0, getItemCount());
        } else {
            removeOnItemClickListener(this);
        }
    }

    @Override
    public void onItemSelectionChanged(RecyclerView.ViewHolder viewHolder, int viewType, boolean isSelected) {
        if (viewType == VIEW_TYPE_NORMAL) {
            CartViewHolder cartAdapterHodel = (CartViewHolder) viewHolder;
            if (isSelected) {
               cartAdapterHodel.img_select.setVisibility(View.VISIBLE);

            } else {
                cartAdapterHodel.img_select.setVisibility(View.GONE);

            }
        }

    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        if (isInSelectedMode() && viewType == VIEW_TYPE_NORMAL) {
            setSelectedItem(position, !isItemSelected(position));
        }

    }

    class  CartViewHolder extends NormalViewHolder{
        private ItemClickListener itemClickListener;
        @BindView(R.id.img_product_detail)
        ImageView img_product_detail;
        @BindView(R.id.tv_name_product)
        TextView tv_name_product;
        @BindView(R.id.img_select)
        ImageButton img_select;
        @BindView(R.id.tv_cost_product)
        TextView tv_cost_product;
        @BindView(R.id.txt_number)
        TextView txt_number;
        @BindView(R.id.img_add)
        ImageButton img_add;
        @BindView(R.id.img_sub)
        ImageButton img_sub;

        public CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
    public interface ClickChangeCount{
        public void clickAddCount(int possition);
        public void clickSubCount(int possition);
    }
}
