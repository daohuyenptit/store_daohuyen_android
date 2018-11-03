package com.daohuyen.dell.store_cosmetics.presenters.shop.list_category;

import android.content.Context;
import android.widget.Toast;


import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;
import com.daohuyen.dell.store_cosmetics.view.fragment.home.HomeFragmentView;

import java.util.List;


public class CategoryPresenterImpl implements CategoryPresenter {
    private Context context;
    private HomeFragmentView homeFragmentView;
    private CategoryInterator categoryInterator;

    public CategoryPresenterImpl(Context context, HomeFragmentView homeFragmentView) {
        this.context = context;
        this.homeFragmentView = homeFragmentView;
        this.categoryInterator = new CategoryInteratorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        categoryInterator.onViewDestroy();
    }

    @Override
    public void loadAllCategory() {
        homeFragmentView.showLoading();
       categoryInterator.getCategory(new OnGetCategorySuccessListener() {
           @Override
           public void onSuccess(List<CategoryViewModel> categories) {
               homeFragmentView.loadAllCategories(categories);
               homeFragmentView.hideLoading();

           }

           @Override
           public void onError(String msg) {
               Toast.makeText(context, "Co loi xay ra", Toast.LENGTH_SHORT).show();
               homeFragmentView.hideLoading();

           }
       });
    }
}
