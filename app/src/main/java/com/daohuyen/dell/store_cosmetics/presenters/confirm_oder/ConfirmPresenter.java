package com.daohuyen.dell.store_cosmetics.presenters.confirm_oder;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface ConfirmPresenter extends BasePresenter {
    void sendBill(BillBody billBody);
}
