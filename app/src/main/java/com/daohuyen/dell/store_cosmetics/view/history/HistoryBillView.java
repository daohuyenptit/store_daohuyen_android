package com.daohuyen.dell.store_cosmetics.view.history;

import com.daohuyen.dell.store_cosmetics.model.view.BillView;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;

import java.util.List;

public interface HistoryBillView {
    void showLoading();
    void hideLoading();
    void loadAllHistoryBills(List<BillView> list);
}
