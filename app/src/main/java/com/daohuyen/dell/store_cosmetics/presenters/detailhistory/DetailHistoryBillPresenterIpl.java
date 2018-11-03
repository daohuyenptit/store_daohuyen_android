package com.daohuyen.dell.store_cosmetics.presenters.detailhistory;

import android.content.Context;
import android.widget.Toast;
import com.daohuyen.dell.store_cosmetics.model.view.BillViewItem;
import com.daohuyen.dell.store_cosmetics.presenters.historybill.HistoryInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.historybill.HistoryInteractorIpl;
import com.daohuyen.dell.store_cosmetics.presenters.historybill.Onsuccess;
import com.daohuyen.dell.store_cosmetics.view.detailHistory.DetailView;

public class DetailHistoryBillPresenterIpl implements DetailHistoryPresenter {
    private Context context;
    private DetailView detailView;
    private DetailHistoryInteractor historyInteractor;

    public DetailHistoryBillPresenterIpl(Context context, DetailView detailView) {
        this.context = context;
        this.detailView = detailView;
        this.historyInteractor=new DetailHistoryInteractorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        historyInteractor.onViewDestroy();

    }

    @Override
    public void loadBill(String billID) {
        detailView.showProgress();
        historyInteractor.getBill(billID,new OnsuccessDetail() {
            @Override
            public void OnsuccessComplete(BillViewItem billViews) {
                detailView.showDetailBill(billViews);
                detailView.hideProgress();
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
               detailView.hideProgress();

            }
        });

    }
}
