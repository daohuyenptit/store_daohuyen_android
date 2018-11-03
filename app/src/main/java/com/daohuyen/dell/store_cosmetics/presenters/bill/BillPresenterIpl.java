package com.daohuyen.dell.store_cosmetics.presenters.bill;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.store_cosmetics.MainActivity;
import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.view.bill.BillView;
import com.daohuyen.dell.store_cosmetics.view.fragment.home.HomeFragment;

public class BillPresenterIpl implements BillPresenter {
    private Context context;
    private BillView billView;
    private BillInterator billInterator;

    public BillPresenterIpl(Context context, BillView billView) {
        this.context = context;
        this.billView = billView;
        this.billInterator=new BillInteratorIpl(context);
    }





    @Override
    public void sendBill(BillBody billBody) {
        billView.showLoadingDialog();
        billInterator.createBill(billBody, new OnGetBillSuccess() {
            @Override
            public void onRateSuccess() {
                billView.hideLoadingDialog();
                billView.getSugetSSucces();
//                billView.backToHomeScreen();

            }

            @Override
            public void onError(String message) {
                billView.hideLoadingDialog();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onViewDestroy() {
        this.billInterator.onViewDestroy();

    }
}
