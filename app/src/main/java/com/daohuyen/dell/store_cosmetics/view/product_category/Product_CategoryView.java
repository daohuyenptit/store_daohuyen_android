package com.daohuyen.dell.store_cosmetics.view.product_category;

import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface Product_CategoryView {
    void showLoadMoreProgress();
    void hideLoadMoreProgress();
    void enableLoadMore(boolean enable);
    void enableRefreshing(boolean enable);
    void showRefreshingProgress();
    void hideRefreshingProgress();
    void addProductPreviews(PageList<ProductViewModel> productViewModelPageList);
    void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList);

}
