package com.daohuyen.dell.store_cosmetics.presenters.detailhistory;

import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface DetailHistoryPresenter extends BasePresenter {
    void loadBill(String billID);
}
