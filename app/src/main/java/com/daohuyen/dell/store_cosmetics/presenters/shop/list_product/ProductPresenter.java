package com.daohuyen.dell.store_cosmetics.presenters.shop.list_product;


import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface ProductPresenter extends BasePresenter {
    void loadMoreProductPreviews(String categoryID);
    void refreshProductPreviews(String categoryID);

}
