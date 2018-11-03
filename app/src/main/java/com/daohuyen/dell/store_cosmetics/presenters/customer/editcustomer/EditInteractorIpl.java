package com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.ResponseCode;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody1;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.auth.CustomerService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class EditInteractorIpl implements EditInteractor {
    Context context;
    CompositeDisposable compositeDisposable;

    public EditInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void editCustomer(CustomerBody1 customer, OnGetEditSuccess listener) {
        String customerID = Utils.getSharePreferenceValues(context, Constants.CUSTOMER_ID);
        Observable<Response<ResponseBody<CustomerView>>> observable = ApiClient.getClient().create(CustomerService.class)
                .editCustomerInform(customerID, customer);
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onSuccess(response.body().getData());
                                    break;
                                }
                                case ResponseCode.NOT_FOUND: {
                                    listener.onError(response.message());
                                    break;
                                }
                                default:{
                                    listener.onError(response.message()+"deafault");
                                    break;
                                }
                            }
                        },
                        error -> {
                            listener.onError(error.getMessage());
                        }
                );
        compositeDisposable.add(disposable);

    }

    @Override
    public void onViewDestroy() {

    }

}
