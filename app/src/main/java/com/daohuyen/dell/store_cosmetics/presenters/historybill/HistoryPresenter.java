package com.daohuyen.dell.store_cosmetics.presenters.historybill;

import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface HistoryPresenter extends BasePresenter {
    void loadAllBills(String customerID);
}
