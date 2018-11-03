package com.daohuyen.dell.store_cosmetics.presenters.login;


import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;

public interface OnGetLoginSuccessListener {
    void onSuccess(CustomerView customer);
    void onError(String msg);
}
