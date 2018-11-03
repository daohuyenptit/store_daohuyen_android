package com.daohuyen.dell.store_cosmetics.presenters.confirm_oder;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.presenters.bill.OnGetBillSuccess;
import com.daohuyen.dell.store_cosmetics.view.cart.CartView;
import com.daohuyen.dell.store_cosmetics.view.confirm_order.ConfirmView;

public class ConfirmPresenterIpl implements ConfirmPresenter {
    private Context context;
    private ConfirmView confirmView;
    private ConfirmInteractor confirmInteractor;

    public ConfirmPresenterIpl(Context context, ConfirmView confirmView) {
        this.context = context;
        this.confirmView = confirmView;
        this.confirmInteractor=new ConfirmInteractorIpl(context);
    }


    @Override
    public void sendBill(BillBody billBody) {
        confirmView.showProgress();
        confirmInteractor.createBill(billBody, new OnGetBillSuccess() {
            @Override
            public void onRateSuccess() {
                confirmView.hideProgress();
                confirmView.getSugetSSucces();
//                billView.backToHomeScreen();

            }

            @Override
            public void onError(String message) {
                confirmView.hideProgress();
                Toast.makeText(context, "Loi roi", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onViewDestroy() {
        confirmInteractor.onViewDestroy();

    }
}
