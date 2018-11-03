package com.daohuyen.dell.store_cosmetics.presenters.searchproduct;

import android.content.Context;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.ToastUtils;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.view.searchproduct.SearchProductView;

public class SearchPresenterIpl implements SearchPresenter {
    private Context context;
    private SearchProductView searchProductView;
    private SearchInterator searchInterator;
    private int currentpage=0;

    public SearchPresenterIpl(Context context, SearchProductView searchProductView) {
        this.context = context;
        this.searchProductView = searchProductView;
        this.searchInterator=new SearchInteractorIpl(context);
    }


    public void onViewDestroy() {
        searchInterator.onViewDestroy();

    }

    @Override
    public void loadMoreProductPreviews() {
        searchProductView.showLoadMoreProgress();
        searchProductView.enableRefreshing(false);
        searchInterator.getProduct(currentpage + 1, 1000, new OnGetProductSuccess() {
            @Override
            public void onSuccess(PageList<ProductViewModel> productViewModelPageList) {
                currentpage++;
                searchProductView.hideLoadMoreProgress();
                searchProductView.enableRefreshing(true);
                if (productViewModelPageList.getPageIndex() == productViewModelPageList.getTotalPage() - 1) {
                    searchProductView.enableLoadMore(false);
                }
                searchProductView.addProductPreviews(productViewModelPageList);

            }



            @Override
            public void onError(String msg) {
                searchProductView.hideLoadMoreProgress();
                searchProductView.enableRefreshing(true);
                ToastUtils.quickToast(context, R.string.error_message);

            }
        });

    }

    @Override
    public void refreshProductPreviews() {
        searchProductView.showRefreshingProgress();
        searchProductView.enableRefreshing(true);
        searchProductView.enableLoadMore(false);
        searchInterator.getProduct(0, 1000, new OnGetProductSuccess() {
            @Override
            public void onSuccess(PageList<ProductViewModel> productViewModelPageList) {
                currentpage=0;
                if (productViewModelPageList.getPageIndex() == productViewModelPageList.getTotalPage() - 1) {
                    searchProductView.enableLoadMore(false);
                }
                searchProductView.hideRefreshingProgress();
                searchProductView.enableLoadMore(true);
                searchProductView.refreshProductPreview(productViewModelPageList);

            }



            @Override
            public void onError(String msg) {
                searchProductView.hideRefreshingProgress();
                searchProductView.enableRefreshing(true);
                searchProductView.hideLoadMoreProgress();
                searchProductView.enableLoadMore(true);
                ToastUtils.quickToast(context, R.string.error_message);

            }
        });




    }
}
