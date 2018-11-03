package com.daohuyen.dell.store_cosmetics.presenters.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody;
import com.daohuyen.dell.store_cosmetics.view.user.login.LoginActivity;
import com.daohuyen.dell.store_cosmetics.view.user.register.RegisterView;
public class RegisterPresenterImpl implements RegisterPresenter {
    private Context context;
    private RegisterView registerView;
    private RegisterInteractor registerInterator;

    public RegisterPresenterImpl(Context context, RegisterView registerView) {
        this.context = context;
        this.registerView = registerView;
        this.registerInterator = new RegisterInteratorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        registerInterator.onViewDestroy();
    }

    @Override
    public void validateUsernameAndPassword(CustomerBody body) {
        if (body.getFullname().isEmpty()) {
            registerView.showUserNameError();
            return;
        }
        if (body.getPassword().isEmpty()) {
            registerView.showPasswordError();
            return;
        }

        registerView.showLoadingDialog();

        registerInterator.register(body, new OnRegisterCompleteListener() {
            @Override
            public void onRegisterSuccess(String username) {
                registerView.hideLoadingDialog();
                Toast.makeText(context, context.getString(R.string.register_successfully), Toast.LENGTH_SHORT).show();
                registerView.finishActivity();
            }

            @Override
            public void onError(String message) {
                registerView.hideLoadingDialog();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAccountExist() {
                registerView.hideLoadingDialog();
                Toast.makeText(context, context.getString(R.string.account_existed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void validateFullName(String fullname) {
        if (fullname.isEmpty()) {
            registerView.showFullNameError();
            return;
        }
    }

    @Override
    public void validatePhone(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            registerView.showPhoneError();
            return;
        }
    }

    @Override
    public void validateAddress(String address) {
        if (address.isEmpty()) {
            registerView.showAddressError();
            return;
        }
    }
}
