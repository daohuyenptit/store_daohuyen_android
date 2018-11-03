package com.daohuyen.dell.store_cosmetics.view.fragment.home;


import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;

import java.util.List;

public interface HomeFragmentView {
    void showLoading();
    void hideLoading();
    void loadAllCategories(List<CategoryViewModel> list);
}
