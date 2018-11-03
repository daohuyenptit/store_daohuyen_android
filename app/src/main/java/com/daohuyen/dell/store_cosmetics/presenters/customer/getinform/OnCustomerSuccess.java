package com.daohuyen.dell.store_cosmetics.presenters.customer.getinform;

import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

public interface OnCustomerSuccess {
    void onGetCustomerComplete(CustomerView customerView);
    void onMessageEror(String msg);
}
