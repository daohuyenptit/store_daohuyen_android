package com.daohuyen.dell.store_cosmetics.presenters.shop.list_category;


import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface CategoryInterator extends BaseInteractor {
    void getCategory(OnGetCategorySuccessListener onGetCategorySuccessListener);
}
