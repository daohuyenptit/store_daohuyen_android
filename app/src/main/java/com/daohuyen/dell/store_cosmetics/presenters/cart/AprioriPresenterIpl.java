package com.daohuyen.dell.store_cosmetics.presenters.cart;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.bill.OnGetBillSuccess;
import com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product.ProductDetailInterator;
import com.daohuyen.dell.store_cosmetics.view.cart.CartView;
import com.daohuyen.dell.store_cosmetics.view.product_detail.ProductDetailView;

import java.util.List;

public class AprioriPresenterIpl implements AprioriPresenter {
    private Context context;
    private CartView cartView;
    private AprioriInteractor aprioriInteractor;

    public AprioriPresenterIpl(Context context, CartView cartView) {
        this.context = context;
        this.cartView = cartView;
        this.aprioriInteractor=new AprioriIpl(context);
    }

    @Override
    public void fetchProductApriori(String input) {
        cartView.showProgress();
        aprioriInteractor.getProductApriori(input, new OnGetApriori() {
            @Override
            public void onGetAprioriComplete(List<ProductViewModel> productViewModel) {

                cartView.showProductApriori(productViewModel);
                cartView.hideProgress();
            }

            @Override
            public void onMessageEror(String msg) {
                cartView.hideProgress();

            }
        });

    }



    @Override
    public void onViewDestroy() {
        aprioriInteractor.onViewDestroy();

    }
}
