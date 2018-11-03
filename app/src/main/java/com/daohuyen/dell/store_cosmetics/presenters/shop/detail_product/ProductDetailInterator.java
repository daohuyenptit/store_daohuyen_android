package com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product;


import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.cart.OnGetApriori;

public interface ProductDetailInterator extends BaseInteractor {
    void getProductDetail(String productID, OnGetProductDetailCompleteListener listener);
    void getProductApriori(String input,OnGetApriori listener);
   // void saveClothes(String clothesID, OnRequestCompleteListener listener);
    //void deleteSavedClothes(String clothesID, OnRequestCompleteListener listener);
   // void getSimilarClothes(String clothesID, int pageIndex, int pageSize, OnGetPageClothesPreviewCompleteListener listener);
   // void rateClothes(String clothesID, RateClothesBody rateClothesBody, OnRequestCompleteListener listener);
    //void getAllRateClothes(String clothesID,OnGetPageRateClothesSuccessListener listener);
    //void orderClothes( String clothesID, OrderBody orderBody,OnRequestCompleteListener listener);
    void getProductState(String productID, OnGetProductStateSuccessListener listener);
}
