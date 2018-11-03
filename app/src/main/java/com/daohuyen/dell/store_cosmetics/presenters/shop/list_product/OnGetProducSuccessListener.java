package com.daohuyen.dell.store_cosmetics.presenters.shop.list_product;


import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface OnGetProducSuccessListener {
    void onGetPageProductPreviewsSuccess(PageList<ProductViewModel> list);
    void onMessageEror(String msg);
}
