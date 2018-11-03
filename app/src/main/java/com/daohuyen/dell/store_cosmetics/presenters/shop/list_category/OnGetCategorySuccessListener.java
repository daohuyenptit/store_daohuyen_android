package com.daohuyen.dell.store_cosmetics.presenters.shop.list_category;


import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;

import java.util.List;

public interface OnGetCategorySuccessListener {
    void onSuccess(List<CategoryViewModel> categories);
    void onError(String msg);
}
