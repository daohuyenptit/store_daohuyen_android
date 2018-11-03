package com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product;

import android.content.Context;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.cart.OnGetApriori;
import com.daohuyen.dell.store_cosmetics.view.product_detail.ProductDetailView;

import java.util.List;


public class ProductDetailPresenterIpl implements ProductDetailPresenter {
    private Context context;
    private ProductDetailView productDetailView;
    private ProductDetailInterator productDetailInterator;

    public ProductDetailPresenterIpl(Context context, ProductDetailView productDetailView) {
        this.context = context;
        this.productDetailView = productDetailView;
        this.productDetailInterator=new ProductDetailInteratorIpl(context);
    }

    @Override
    public void fetchProductDetail(String productID) {
        productDetailView.showLoading();
        productDetailInterator.getProductDetail(productID, new OnGetProductDetailCompleteListener() {
            @Override
            public void onGetProductDetailComplete(ProductViewModel productViewModel) {
                productDetailView.showProductDetail(productViewModel);
                productDetailView.hideLoading();
            }

            @Override
            public void onMessageEror(String msg) {
                productDetailView.hideLoading();

            }
        });

    }

    @Override
    public void fetchProductApriori(String input) {
        productDetailView.showLoading();
        productDetailInterator.getProductApriori(input, new OnGetApriori() {
            @Override
            public void onGetAprioriComplete(List<ProductViewModel> productViewModel) {
                productDetailView.showProductApriori(productViewModel);
                productDetailView.hideLoading();

            }

            @Override
            public void onMessageEror(String msg) {
                productDetailView.hideLoading();

            }
        });

    }




    @Override
    public void onViewDestroy() {
        this.productDetailInterator.onViewDestroy();

    }
}
