package com.daohuyen.dell.store_cosmetics.services.retrofit.auth;

import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody1;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerService {
    @PUT("api/customers/profiles/{id}")
    Observable<Response<ResponseBody<CustomerView>>> editCustomerInform(@Path("id") String customerID, @Body CustomerBody1 customerBody1);
}
