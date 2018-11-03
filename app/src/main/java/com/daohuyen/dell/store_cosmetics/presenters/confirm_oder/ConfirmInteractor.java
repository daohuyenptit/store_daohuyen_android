package com.daohuyen.dell.store_cosmetics.presenters.confirm_oder;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.bill.OnGetBillSuccess;
import com.daohuyen.dell.store_cosmetics.presenters.cart.OnGetApriori;

public interface ConfirmInteractor extends BaseInteractor {
    void createBill(BillBody billBody, OnGetBillSuccess listener);
}
