package com.daohuyen.dell.store_cosmetics.view.product_detail;


import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface ProductDetailView {
    void showLoading();
    void hideLoading();
    void showProductDetail(ProductViewModel productViewModel);
    void showProductApriori(List<ProductViewModel> productViewModel);
}
