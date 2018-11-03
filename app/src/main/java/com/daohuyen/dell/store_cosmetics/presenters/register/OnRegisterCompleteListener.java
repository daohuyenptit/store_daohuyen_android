package com.daohuyen.dell.store_cosmetics.presenters.register;

public interface OnRegisterCompleteListener {
    void onRegisterSuccess(String fullname);
    void onError(String message);
    void onAccountExist();
}
