package com.daohuyen.dell.store_cosmetics.presenters.historybill;

import android.content.Context;
import android.widget.Toast;
import com.daohuyen.dell.store_cosmetics.model.view.BillView;
import com.daohuyen.dell.store_cosmetics.view.history.HistoryBillView;
import java.util.List;

public class HistoryBillPresenterIpl implements HistoryPresenter {
    private Context context;
    private HistoryBillView historyBillView;
    private HistoryInteractor historyInteractor;

    public HistoryBillPresenterIpl(Context context, HistoryBillView historyBillView) {
        this.context = context;
        this.historyBillView = historyBillView;
        this.historyInteractor=new HistoryInteractorIpl(context);
    }

    @Override
    public void loadAllBills(String customerID) {
        historyBillView.showLoading();
        historyInteractor.getAllHistory(customerID,new Onsuccess() {
            @Override
            public void OnsuccessComplete(List<BillView> billViews) {
                historyBillView.loadAllHistoryBills(billViews);
                historyBillView.hideLoading();
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                historyBillView.hideLoading();

            }
        });

    }

    @Override
    public void onViewDestroy() {
        historyInteractor.onViewDestroy();

    }
}
