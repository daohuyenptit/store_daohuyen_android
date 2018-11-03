package com.daohuyen.dell.store_cosmetics.view.detailHistory;

import com.daohuyen.dell.store_cosmetics.model.view.BillViewItem;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

public interface DetailView {
    void showProgress();
    void hideProgress();
    void showDetailBill(BillViewItem billViewItem);
}
