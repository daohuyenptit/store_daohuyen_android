package com.daohuyen.dell.store_cosmetics.presenters.login;


import com.daohuyen.dell.store_cosmetics.model.body.UserBody;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface LoginInterator extends BaseInteractor {
    void getLogin(UserBody userBody, OnGetLoginSuccessListener onGetLoginSuccessListener);
}
