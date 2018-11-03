package com.daohuyen.dell.store_cosmetics.presenters.searchproduct;

import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.Product;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface OnGetProductSuccess {
    void onSuccess(PageList<ProductViewModel> productViewModels);
    void onError(String msg);
}
