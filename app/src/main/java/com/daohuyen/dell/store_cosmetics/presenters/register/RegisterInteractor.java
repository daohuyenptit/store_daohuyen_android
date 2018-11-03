package com.daohuyen.dell.store_cosmetics.presenters.register;

import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody;
import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface RegisterInteractor extends BaseInteractor {
    void register(
                  CustomerBody body,
                  OnRegisterCompleteListener listener);
}
