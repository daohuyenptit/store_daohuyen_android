package com.daohuyen.dell.store_cosmetics.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;
import com.daohuyen.dell.store_cosmetics.view.product_category.Product_CateoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CateAdapter extends EndlessLoadingRecyclerViewAdapter {
    public CateAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initLoadingViewHolder(ViewGroup parent) {
        View loadingView = getInflater().inflate(R.layout.item_load_more, parent, false);
        return new LoadingViewHolder(loadingView);

    }

    @Override
    protected void bindLoadingViewHolder(LoadingViewHolder loadingViewHolder, int position) {

    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view= getInflater().inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        CategoryViewModel categoryViewModel=  getItem(position, CategoryViewModel.class);
        CategoryAdaper.CategoryViewHolder viewHolder= (CategoryAdaper.CategoryViewHolder) holder;
        Glide.with(getContext())
                .load(categoryViewModel.getLogo())
                .apply(new RequestOptions().placeholder(R.drawable.logoapp)).into(viewHolder.img_avatar);

        viewHolder.tv_category.setText(categoryViewModel.getTitle());

    }
    class  CategoryViewHolder extends NormalViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private ItemClickListener itemClickListener;
        @BindView(R.id.img_avatar)
        ImageView img_avatar;
        @BindView(R.id.tv_category)
        TextView tv_category;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {

            Intent intent= new Intent(getContext(), Product_CateoryActivity.class);
            intent.putExtra(Constants.CATEGORY_ID,  getItem(getPosition(),CategoryViewModel.class).getId());
            getContext().startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }
    }
}
