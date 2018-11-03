package com.daohuyen.dell.store_cosmetics.presenters.cart;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.bill.OnGetBillSuccess;

public interface AprioriInteractor extends BaseInteractor {
    void getProductApriori(String input,OnGetApriori listener);
}
