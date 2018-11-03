package com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer;

import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;

public interface OnGetEditSuccess {
    void onSuccess(CustomerView customer);
    void onError(String message);

}
