package com.daohuyen.dell.store_cosmetics.presenters.detailhistory;
import com.daohuyen.dell.store_cosmetics.model.view.BillViewItem;
public interface OnsuccessDetail {
    void OnsuccessComplete(BillViewItem billViews);
    void OnError(String msg);
}
