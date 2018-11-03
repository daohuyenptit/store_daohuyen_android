package com.daohuyen.dell.store_cosmetics.view.user.register;

public interface RegisterView {
    void showLoadingDialog();
    void hideLoadingDialog();
    void showUserNameError();
    void showPasswordError();
    void showFullNameError();
    void showAddressError();
    void showPhoneError();
    void showInvalidUser();


    void finishActivity();
}
