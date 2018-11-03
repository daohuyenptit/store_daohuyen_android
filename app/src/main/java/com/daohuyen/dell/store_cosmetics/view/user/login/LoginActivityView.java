package com.daohuyen.dell.store_cosmetics.view.user.login;

/**
 * Created by KingIT on 4/7/2018.
 */

public interface LoginActivityView {
    void showProgress();
    void hideProgress();
    void showEmailInputError(String message);
    void showPasswordInputError(String message);
    void backToHomeScreen(int resultCode);
}
