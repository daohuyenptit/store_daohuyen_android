package com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product;

public interface OnGetProductStateSuccessListener {
    void onGetStateSuccess(boolean state);
    void onError(String msg);
}
