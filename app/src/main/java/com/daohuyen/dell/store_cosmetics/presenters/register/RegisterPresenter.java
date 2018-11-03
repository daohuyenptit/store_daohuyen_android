package com.daohuyen.dell.store_cosmetics.presenters.register;

import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody;
import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;


public interface RegisterPresenter extends BasePresenter {
    void validateUsernameAndPassword( CustomerBody body);
    void validateFullName(String fullname);
    void validatePhone(String phoneNumber);
    void validateAddress(String address);

}
