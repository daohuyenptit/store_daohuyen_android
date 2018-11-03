package com.daohuyen.dell.store_cosmetics.view.customer;

public interface EditInformView {
    void showLoadingDiaolog();
    void hideLoadingDialog();
    void showFullNameError();
    void showAddressError();
    void showEmailError();
    void showPhoneError();
    void showIDCardError();
    void backToProfileScreen();
}

