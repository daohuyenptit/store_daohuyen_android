package com.daohuyen.dell.store_cosmetics.presenters.cart;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface AprioriPresenter extends BasePresenter {
    void fetchProductApriori(String input);
}
