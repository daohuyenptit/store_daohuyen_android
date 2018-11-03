package com.daohuyen.dell.store_cosmetics.services.retrofit.auth;
import com.daohuyen.dell.store_cosmetics.model.body.UserBody;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/auths/customer/login")
    Observable<Response<ResponseBody<CustomerView>>> getCustomerLogin(@Body UserBody userBody);
}
