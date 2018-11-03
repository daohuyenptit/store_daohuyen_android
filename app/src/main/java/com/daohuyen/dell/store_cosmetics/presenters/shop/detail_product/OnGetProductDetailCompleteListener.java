package com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product;

import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

public  interface OnGetProductDetailCompleteListener {
    void onGetProductDetailComplete(ProductViewModel productViewModel);
    void onMessageEror(String msg);
}
