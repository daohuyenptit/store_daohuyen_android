package com.daohuyen.dell.store_cosmetics.presenters.searchproduct;

import com.daohuyen.dell.store_cosmetics.model.body.ProductNameKey;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface SearchInterator extends BaseInteractor {
    void getProduct( int pageIndex, int pageSize,OnGetProductSuccess onGetProductSuccess);
}
