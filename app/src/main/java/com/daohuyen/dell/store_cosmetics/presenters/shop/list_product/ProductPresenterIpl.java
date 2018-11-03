package com.daohuyen.dell.store_cosmetics.presenters.shop.list_product;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.ToastUtils;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.view.product_category.Product_CategoryView;

import java.util.List;

public class ProductPresenterIpl implements ProductPresenter {
    private Context context;
    private Product_CategoryView product_categoryView;
    private ProductInterator productInterator;
    private int currentpage=0;

    public ProductPresenterIpl(Context context, Product_CategoryView product_categoryView) {
        this.context = context;
        this.product_categoryView = product_categoryView;
        this.productInterator=new ProductInteratorIpl(context);
    }



    @Override
    public void onViewDestroy() {
        productInterator.onViewDestroy();

    }



    @Override
    public void loadMoreProductPreviews(String categoryID) {
        product_categoryView.showLoadMoreProgress();
        product_categoryView.enableRefreshing(false);
        productInterator.getProduct(categoryID, currentpage + 1, Constants.PAGE_SIZE, new OnGetProducSuccessListener() {
            @Override
            public void onGetPageProductPreviewsSuccess(PageList<ProductViewModel> productViewModelPageList) {
                currentpage++;
                product_categoryView.hideLoadMoreProgress();
                product_categoryView.enableRefreshing(true);
                if (productViewModelPageList.getPageIndex() == productViewModelPageList.getTotalPage() - 1) {
                    product_categoryView.enableLoadMore(false);
                }
                product_categoryView.addProductPreviews(productViewModelPageList);

            }



            @Override
            public void onMessageEror(String msg) {
                product_categoryView.hideLoadMoreProgress();
                product_categoryView.enableRefreshing(true);
                ToastUtils.quickToast(context, R.string.error_message);

            }
        });


    }

    @Override
    public void refreshProductPreviews(String categoryID) {
        product_categoryView.showRefreshingProgress();
        product_categoryView.enableRefreshing(true);
        product_categoryView.enableLoadMore(false);
        productInterator.getProduct(categoryID, 0, Constants.PAGE_SIZE, new OnGetProducSuccessListener() {
            @Override
            public void onGetPageProductPreviewsSuccess(PageList<ProductViewModel> productViewModelPageList) {
                currentpage=0;
                if (productViewModelPageList.getPageIndex() == productViewModelPageList.getTotalPage() - 1) {
                    product_categoryView.enableLoadMore(false);
                }
                product_categoryView.hideRefreshingProgress();
                product_categoryView.enableLoadMore(true);
                product_categoryView.refreshProductPreview(productViewModelPageList);

            }



            @Override
            public void onMessageEror(String msg) {
                product_categoryView.hideRefreshingProgress();
                product_categoryView.enableRefreshing(true);
                product_categoryView.hideLoadMoreProgress();
                product_categoryView.enableLoadMore(true);
                ToastUtils.quickToast(context, R.string.error_message);

            }
        });

    }



}
