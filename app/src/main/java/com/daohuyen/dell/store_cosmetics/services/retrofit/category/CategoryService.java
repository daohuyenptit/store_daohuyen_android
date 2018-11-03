package com.daohuyen.dell.store_cosmetics.services.retrofit.category;

import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("api/products/category")
    Observable<Response<ResponseBody<List<CategoryViewModel>>>> getAllCategory();
}
