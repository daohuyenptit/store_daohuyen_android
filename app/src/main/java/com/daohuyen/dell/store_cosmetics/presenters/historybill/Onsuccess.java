package com.daohuyen.dell.store_cosmetics.presenters.historybill;

import com.daohuyen.dell.store_cosmetics.model.view.BillView;
import java.util.List;

public interface Onsuccess {
    void OnsuccessComplete(List<BillView> billViews);
    void OnError(String msg);
}
