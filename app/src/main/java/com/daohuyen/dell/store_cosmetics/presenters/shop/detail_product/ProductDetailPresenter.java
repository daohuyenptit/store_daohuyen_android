package com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product;


import com.daohuyen.dell.store_cosmetics.presenters.BasePresenter;

public interface ProductDetailPresenter extends BasePresenter {
    void fetchProductDetail(String productID);
    void fetchProductApriori(String productID);
//    void saveClothes(String clothesID);
//    void deleteSavedClothes(String clothesID);
//    void firstFetchSimilarClothes(String clothesID);
//    void loadMoreSimilarClothes(String clothesID);
//    void rateClothes(String clothesID, RateClothesBody rateClothesBody);
//    void getAllRateClothes(String clothesID);
//    void orderClothes(String clothesID, OrderBody orderBody);
}
