package com.daohuyen.dell.store_cosmetics.presenters.cart;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.ResponseCode;
import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.presenters.bill.OnGetBillSuccess;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.bill.BillService;
import com.daohuyen.dell.store_cosmetics.services.retrofit.product.ProductService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AprioriIpl implements AprioriInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public AprioriIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getProductApriori(String input, OnGetApriori listener) {
        Disposable disposable = ApiClient.getClient().create(ProductService.class)
                .getAllProductByApriori(input)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onGetAprioriComplete(response.body().getData());
                                    break;
                                }
                                case ResponseCode.NOT_FOUND: {
                                    listener.onMessageEror(response.message());
                                    break;
                                }
                                default: {
                                    listener.onMessageEror(response.message());
                                    break;
                                }

                            }
                        }, error -> {
                            listener.onMessageEror(context.getString(R.string.server_error));
                        });
        compositeDisposable.add(disposable);

    }


    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();

    }
}
