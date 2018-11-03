package com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.ResponseCode;
import com.daohuyen.dell.store_cosmetics.presenters.cart.OnGetApriori;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.product.ProductService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailInteratorIpl implements ProductDetailInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public ProductDetailInteratorIpl(Context context) {
        this.context = context;
        compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getProductDetail(String productID, OnGetProductDetailCompleteListener listener) {
        Disposable disposable = ApiClient.getClient().create(ProductService.class)
                .getProductDetail(productID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onGetProductDetailComplete(response.body().getData());
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
    public void getProductState(String productID, OnGetProductStateSuccessListener listener) {

    }

    @Override
    public void onViewDestroy() {
        this.compositeDisposable.clear();

    }
}
