package com.daohuyen.dell.store_cosmetics.presenters.bill;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface BillPresenter extends BasePresenter {
    void sendBill(BillBody billBody);
}
