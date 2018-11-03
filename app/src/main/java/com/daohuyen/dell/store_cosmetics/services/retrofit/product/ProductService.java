package com.daohuyen.dell.store_cosmetics.services.retrofit.product;

import com.daohuyen.dell.store_cosmetics.common.RequestConstants;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.response.ResponseBody;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("/api/products/getProducts/{id}")
    Observable<Response<ResponseBody<PageList<ProductViewModel>>>> getAllProductByCategoryID(@Path("id") String categoryID,
                                                                                             @Query(RequestConstants.PAGE_INDEX_QUERY) int pageIndex,
                                                                                             @Query(RequestConstants.PAGE_SIZE_QUERY) int pageSize,
                                                                                             @Query(RequestConstants.SORT_BY_QUERY) String sortBy,
                                                                                             @Query(RequestConstants.SORT_TYPE_QUERY) String sortType);

    @GET("/api/products/productdetail/{id}")
    Observable<Response<ResponseBody<ProductViewModel>>> getProductDetail(@Path("id") String productID);

    @GET("/api/products/getProducts/{key}")
    Observable<Response<ResponseBody<PageList<ProductViewModel>>>> getAllProductBySearch(@Path("key") String keyword,
                                                                                             @Query(RequestConstants.PAGE_INDEX_QUERY) int pageIndex,
                                                                                             @Query(RequestConstants.PAGE_SIZE_QUERY) int pageSize,
                                                                                             @Query(RequestConstants.SORT_BY_QUERY) String sortBy,
                                                                                             @Query(RequestConstants.SORT_TYPE_QUERY) String sortType);

    @GET("/api/apriori/productsuggest/{inputcate}")
    Observable<Response<ResponseBody<List<ProductViewModel>>>> getAllProductByApriori(@Path("inputcate") String inputcate);

    @GET("/api/products/products")
    Observable<Response<ResponseBody<PageList<ProductViewModel>>>> getAllProduct(@Query(RequestConstants.PAGE_INDEX_QUERY) int pageIndex,
                                                                                 @Query(RequestConstants.PAGE_SIZE_QUERY) int pageSize,
                                                                                 @Query(RequestConstants.SORT_BY_QUERY) String sortBy,
                                                                                 @Query(RequestConstants.SORT_TYPE_QUERY) String sortType);
}