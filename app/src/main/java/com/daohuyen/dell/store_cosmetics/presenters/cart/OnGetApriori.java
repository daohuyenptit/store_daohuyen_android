package com.daohuyen.dell.store_cosmetics.presenters.cart;

import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface OnGetApriori {
    void onGetAprioriComplete(List<ProductViewModel> productViewModel);
    void onMessageEror(String msg);
}
