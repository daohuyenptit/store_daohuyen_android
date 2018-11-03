package com.daohuyen.dell.store_cosmetics.view.cart;

import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface CartView {
    void showProgress();
    void hideProgress();
    void showProductApriori(List<ProductViewModel> productViewModel);
}
