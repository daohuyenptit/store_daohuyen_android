package com.daohuyen.dell.store_cosmetics.services.retrofit.historybill;

import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.BillView;
import com.daohuyen.dell.store_cosmetics.model.view.BillViewItem;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HistoryBillService {
    @GET("api/history/getHistories/{id}")
    Observable<Response<ResponseBody<List<BillView>>>> getAllBills(@Path("id") String customerID);
    @GET("api/bill/getbill/{id}")
    Observable<Response<ResponseBody<BillViewItem>>> getBill(@Path("id") String billID);
}
