package com.daohuyen.dell.store_cosmetics.presenters.historybill;

import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;

public interface HistoryInteractor extends BaseInteractor {
    void getAllHistory(String customerID,Onsuccess onsuccess);
}
