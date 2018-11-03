package com.daohuyen.dell.store_cosmetics.presenters.detailhistory;

import com.daohuyen.dell.store_cosmetics.presenters.BaseInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.historybill.Onsuccess;

public interface DetailHistoryInteractor extends BaseInteractor {
    void getBill(String billID, OnsuccessDetail onsuccess);
}
