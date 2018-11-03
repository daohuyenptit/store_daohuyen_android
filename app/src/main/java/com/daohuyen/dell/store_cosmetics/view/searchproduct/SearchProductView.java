package com.daohuyen.dell.store_cosmetics.view.searchproduct;

import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

public interface SearchProductView {
    void showLoadMoreProgress();
    void hideLoadMoreProgress();
    void enableLoadMore(boolean enable);
    void enableRefreshing(boolean enable);
    void showRefreshingProgress();
    void hideRefreshingProgress();
    void addProductPreviews(PageList<ProductViewModel> productViewModelPageList);
    void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList);
}
