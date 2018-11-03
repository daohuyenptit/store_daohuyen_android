package com.daohuyen.dell.store_cosmetics.presenters.confirm_oder;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.ResponseCode;
import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.presenters.bill.OnGetBillSuccess;
import com.daohuyen.dell.store_cosmetics.presenters.cart.AprioriInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.cart.OnGetApriori;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.bill.BillService;
import com.daohuyen.dell.store_cosmetics.services.retrofit.product.ProductService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ConfirmInteractorIpl implements ConfirmInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public ConfirmInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void createBill(BillBody billBody, OnGetBillSuccess listener) {
        Observable<Response<ResponseBody<String>>> observable =
                ApiClient.getClient().create(BillService.class).creatBills(billBody);
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onRateSuccess();
                                    break;
                                }
                                case ResponseCode.NOT_FOUND: {
                                    listener.onError(response.message());
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
        compositeDisposable.clear();

    }
}
