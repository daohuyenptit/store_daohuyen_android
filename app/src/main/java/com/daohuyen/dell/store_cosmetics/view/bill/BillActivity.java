package com.daohuyen.dell.store_cosmetics.view.bill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.daohuyen.dell.store_cosmetics.MainActivity;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.BillAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.CartSingleton;
import com.daohuyen.dell.store_cosmetics.model.Item;
import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.body.LotProductBody;
import com.daohuyen.dell.store_cosmetics.presenters.bill.BillPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.bill.BillPresenterIpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillActivity extends AppCompatActivity implements BillView{
    @BindView(R.id.bt_pay)
    Button bt_pay;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.rcv_cart)
    RecyclerView recycler;
    BillAdapter adapter;
    ArrayList<Item> items;
    @BindView(R.id.txt_total)
    TextView total;
    BillPresenter presenter;
    LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Hóa đơn mua hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txt_name.setText(Utils.getSharePreferenceValues(this,Constants.CUSTOMER_NAME));
        presenter=new BillPresenterIpl(this,this);
        loadingDialog=new LoadingDialog(this);
        adapter=new BillAdapter(this,false);
        adapter.addModels(CartSingleton.getCart().getItems(),false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        int total1=0;
        for (int i = 0; i <CartSingleton.getCart().getItems().size() ; i++) {
            total1+=CartSingleton.getCart().getItems().get(i).getProduct().getPrice()*CartSingleton.getCart().getItems().get(i).getNumber();

        }
        total.setText(Utils.formatNumberMoney(total1)+" "+"Đ");
//        bt_pay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Set<LotProductBody> lotProductBodies=new HashSet<>();
//                for (int i = 0; i < CartSingleton.getCart().getItems().size(); i++) {
//                    lotProductBodies.add(new LotProductBody(CartSingleton.getCart().getItems().get(i).getProduct().getId(),CartSingleton.getCart().getItems().get(i).getProduct().getPrice(),CartSingleton.getCart().getItems().get(i).getNumber()));
//
//                }
//                BillBody billBody=new BillBody(Utils.getSharePreferenceValues(getApplicationContext(), Constants.CUSTOMER_ID),lotProductBodies);
//                if(Utils.checkNetwork(getApplicationContext())){
//                    presenter.sendBill(billBody);
//
//                }
//
//            }
//        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();

    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.hide();

    }



    @Override
    public void backToHomeScreen() {
        finish();

    }

    @Override
    public void getSugetSSucces(){
        CartSingleton.getCart().getItems().clear();
        startActivity(new Intent(BillActivity.this, MainActivity.class));
        finish();

    }
}
