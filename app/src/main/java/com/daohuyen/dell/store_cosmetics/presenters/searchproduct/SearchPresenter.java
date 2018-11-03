package com.daohuyen.dell.store_cosmetics.presenters.searchproduct;

import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface SearchPresenter extends BasePresenter {
    void loadMoreProductPreviews();
    void refreshProductPreviews();
}
