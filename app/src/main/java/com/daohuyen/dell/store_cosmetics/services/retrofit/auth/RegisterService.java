package com.daohuyen.dell.store_cosmetics.services.retrofit.auth;

import com.daohuyen.dell.store_cosmetics.common.RequestConstants;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("/api/customers/register")
    Observable<Response<ResponseBody<String>>> CustomerRegister(@Header(RequestConstants.AUTHORIZATION) String base64Account,
                                                                @Body CustomerBody body);
}
