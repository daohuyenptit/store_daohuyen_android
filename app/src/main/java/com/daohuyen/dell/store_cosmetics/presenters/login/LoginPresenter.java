package com.daohuyen.dell.store_cosmetics.presenters.login;


import com.daohuyen.dell.store_cosmetics.model.body.UserBody;
import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface LoginPresenter extends BasePresenter {
    void fetchLogin(UserBody userBody);


}
