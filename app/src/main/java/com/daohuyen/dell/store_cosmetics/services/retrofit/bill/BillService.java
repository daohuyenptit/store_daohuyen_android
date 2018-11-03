package com.daohuyen.dell.store_cosmetics.services.retrofit.bill;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BillService {
    @POST("/api/bill/createBills")
    Observable<Response<ResponseBody<String>>> creatBills( @Body BillBody billBody);
}
