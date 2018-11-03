package com.daohuyen.dell.store_cosmetics.presenters.historybill;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.BillView;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;
import com.daohuyen.dell.store_cosmetics.services.retrofit.ApiClient;
import com.daohuyen.dell.store_cosmetics.services.retrofit.category.CategoryService;
import com.daohuyen.dell.store_cosmetics.services.retrofit.historybill.HistoryBillService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HistoryInteractorIpl implements HistoryInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public HistoryInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getAllHistory(String customerID,Onsuccess listener) {
        Observable<Response<ResponseBody<List<BillView>>>> observable = ApiClient.getClient()
                .create(HistoryBillService.class).getAllBills(customerID);

        Disposable disposable = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                    listener.OnsuccessComplete(response.body().getData());
                }, error -> {
                    listener.OnError("Co loi roi");
                });
        compositeDisposable.add(disposable);

    }

    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();

    }
}
