package com.daohuyen.dell.store_cosmetics.presenters.register;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Base64UtilAccount;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.ResponseCode;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.auth.RegisterService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RegisterInteratorIpl implements RegisterInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public RegisterInteratorIpl(Context context) {
        this.context = context;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void register(CustomerBody body, OnRegisterCompleteListener listener) {
        //Gọi đến api đăng ký
        Observable<Response<ResponseBody<String>>> observable = ApiClient.getClient().create(RegisterService.class)
                .CustomerRegister(Base64UtilAccount.getBase64Account(body.getUsername(), body.getPassword()), body);
        Disposable disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onRegisterSuccess(response.body().getData());
                                    break;
                                }
                                case ResponseCode.CONFLICT: {
                                    listener.onAccountExist();
                                    break;
                                }
                                case ResponseCode.FORBIDDEN: {
                                    listener.onError(context.getString(R.string.password_must_be_more_than_six_letter));
                                    break;
                                }
                                default:{
                                    //mặc didnh
                                    listener.onError(context.getString(R.string.server_error));
                                }
                            }
                        },
                        error -> {
                            //lỗi server
                            listener.onError(context.getString(R.string.server_error));
                        }
                );

        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewDestroy() {
        context = null;
        compositeDisposable.clear();
    }
}
