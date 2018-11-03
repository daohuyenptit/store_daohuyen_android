package com.daohuyen.dell.store_cosmetics.presenters.shop.list_product;

import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface ProductInterator extends BaseInteractor {
    void getProduct(String categoryID, int pageIndex, int pageSize, OnGetProducSuccessListener onGetProducSuccessListener);
}
