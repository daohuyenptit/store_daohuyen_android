package com.daohuyen.dell.store_cosmetics.presenters;



public interface OnRequestCompleteListener {
    void onSuccess();
    void onServerError(String message);
}
