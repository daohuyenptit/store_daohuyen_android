package com.daohuyen.dell.store_cosmetics.presenters.searchproduct;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.ResponseCode;
import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.body.ProductNameKey;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.auth.LoginService;
import com.daohuyen.dell.store_cosmetics.services.retrofit.category.CategoryService;
import com.daohuyen.dell.store_cosmetics.services.retrofit.product.ProductService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SearchInteractorIpl implements SearchInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public SearchInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getProduct(int pageIndex, int pageSize,OnGetProductSuccess listener) {
        Disposable disposable = ApiClient.getClient().create(ProductService.class)
                .getAllProduct(pageIndex, pageSize,null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                    switch (response.code()) {
                        case ResponseCode.OK: {
                            listener.onSuccess((PageList<ProductViewModel>) response.body().getData());
                            break;
                        }
                        case ResponseCode.NOT_FOUND: {
                            listener.onError(response.message());
                            break;
                        }
                        default:{
                            listener.onError(response.message());
                            break;
                        }
                    }
                }, error -> {
                    listener.onError(context.getString(R.string.server_error));
                });
        compositeDisposable.add(disposable);

    }




    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();

    }
}
