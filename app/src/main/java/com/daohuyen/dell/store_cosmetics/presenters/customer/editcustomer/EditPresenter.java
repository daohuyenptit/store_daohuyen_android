package com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer;

import android.net.Uri;

import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface EditPresenter extends BasePresenter {
    void validateInform(String fullName, String phone, String address, String identityCard, String avatarUrl, int gender,
                         long birthday, String email,String description);
}
