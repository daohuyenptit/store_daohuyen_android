package com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer;

import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody1;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface EditInteractor extends BaseInteractor {
    void editCustomer(CustomerBody1 customerBody1, OnGetEditSuccess listener);
}
