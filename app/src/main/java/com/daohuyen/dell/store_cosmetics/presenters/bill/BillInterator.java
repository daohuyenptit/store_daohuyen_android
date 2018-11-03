package com.daohuyen.dell.store_cosmetics.presenters.bill;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface BillInterator extends BaseInteractor {
    void createBill(BillBody billBody, OnGetBillSuccess listener);
}
