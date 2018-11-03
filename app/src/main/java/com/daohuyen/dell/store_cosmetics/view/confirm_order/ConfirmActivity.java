package com.daohuyen.dell.store_cosmetics.view.confirm_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.CartAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.ConfirmAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.DetailOrderAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.Address;
import com.daohuyen.dell.store_cosmetics.model.CartSingleton;
import com.daohuyen.dell.store_cosmetics.model.Pay;
import com.daohuyen.dell.store_cosmetics.model.Transport;
import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.body.LotProductBody;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.confirm_oder.ConfirmInteractor;
import com.daohuyen.dell.store_cosmetics.presenters.confirm_oder.ConfirmPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.confirm_oder.ConfirmPresenterIpl;
import com.daohuyen.dell.store_cosmetics.view.cart.CartActivity;
import com.daohuyen.dell.store_cosmetics.view.cart.CartView;
import com.daohuyen.dell.store_cosmetics.view.user.login.LoginActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmActivity extends AppCompatActivity implements
        View.OnClickListener,ConfirmView{
    @BindView(R.id.rcv_cart)
    RecyclerView mRecycleView;
    @BindView(R.id.txt_address)
    TextView txt_address;
    @BindView(R.id.txt_transport)
    TextView txt_transport;
    @BindView(R.id.txt_pay)
    TextView txt_pay;
    @BindView(R.id.txt_price)
    TextView txt_price;
    @BindView(R.id.txt_price_trans)
    TextView txt_price_trans;
    @BindView(R.id.img_address)
    ImageView img_address;
    @BindView(R.id.img_pay)
    ImageView img_pay;
    @BindView(R.id.img_transport)
    ImageView img_transport;
    @BindView(R.id.bt_order)
    Button bt_order;
    ConfirmAdapter adapter;
    LoadingDialog loadingDialog;
    int carttotal=0;
    ConfirmPresenter presenter;
    Address address;
    Transport transport;
    Pay pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        loadingDialog=new LoadingDialog(this);
        presenter=new ConfirmPresenterIpl(this,this);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Thanh toán");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        adapter=new ConfirmAdapter(this,false);
        adapter.addModels(CartSingleton.getCart().getItems(),false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);
        adapter.setSelectedMode(true);
        String name= Utils.getSharePreferenceValues(this, Constants.CUSTOMER_NAME);

        address=new Address(name,Utils.getCustomerview(this).getPhone(),Utils.getCustomerview(this).getAddress());
        transport=new Transport("Giao hàng siêu tiết kiệm",15000,"Giao hàng 5-7 ngày");
        pay=new Pay("Thanh toán khi nhận hàng");
        txt_address.setText(name+"\n"+Utils.getCustomerview(this).getPhone()+"\n"+Utils.getCustomerview(this).getAddress());
        img_address.setOnClickListener(this);
        img_transport.setOnClickListener(this);
        img_pay.setOnClickListener(this);
        for (int i = 0; i < CartSingleton.getCart().getItems().size(); i++) {
            carttotal+=CartSingleton.getCart().getItems().get(i).getProduct().getPrice()*CartSingleton.getCart().getItems().get(i).getNumber();

        }
        txt_price.setText(Utils.formatNumberMoney(carttotal+15000)+" Đ");
        bt_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_address:
                startActivityForResult(new Intent(ConfirmActivity.this,AddressActivity.class),Constants.REQUEST_CODE_ADDRESS);
                break;
            case R.id.img_transport:
                startActivityForResult(new Intent(ConfirmActivity.this,TransportActivity.class),Constants.REQUEST_CODE_TRANSPORT);
                break;
            case R.id.img_pay:
                startActivityForResult(new Intent(ConfirmActivity.this,PayActivity.class),Constants.REQUEST_CODE_PAY);
                break;
            case R.id.bt_order:

                    Set<LotProductBody> lotProductBodies=new HashSet<>();
                    for (int i = 0; i < CartSingleton.getCart().getItems().size(); i++) {
                        lotProductBodies.add(new LotProductBody(CartSingleton.getCart().getItems().get(i).getProduct().getId(),CartSingleton.getCart().getItems().get(i).getProduct().getPrice(),CartSingleton.getCart().getItems().get(i).getNumber()));

                    }
                    BillBody billBody=new BillBody(Utils.getSharePreferenceValues(getApplicationContext(), Constants.CUSTOMER_ID),lotProductBodies,
                           address.getName(),address.getPhone(),address.getAddress(),
                            transport.getName(),transport.getPrice(),pay.getName() );
                    if(Utils.checkNetwork(getApplicationContext())){
                        presenter.sendBill(billBody);

                    }

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.REQUEST_CODE_ADDRESS && resultCode==Constants.RESULT_CODE_ADDRESS){
             address= (Address) data.getSerializableExtra(Constants.ADDRESS);
            txt_address.setText(address.getName()+"\n"+address.getPhone()+"\n"+address.getAddress());
        }else if(requestCode==Constants.REQUEST_CODE_TRANSPORT && resultCode==Constants.RESULT_CODE_TRANSPORT){
             transport= (Transport) data.getSerializableExtra(Constants.TRANSPORT);
            txt_transport.setText(transport.getName()+"\n"+transport.getTime());
            txt_price_trans.setText(Utils.formatNumberMoney(transport.getPrice())+" Đ");
            txt_price.setText(Utils.formatNumberMoney(carttotal+transport.getPrice())+" Đ");

        }else if(requestCode==Constants.REQUEST_CODE_PAY && resultCode==Constants.RESULT_CODE_PAY){
             pay= (Pay) data.getSerializableExtra(Constants.PAY);
            txt_pay.setText(pay.getName());

        }
    }


    @Override
    public void showProgress() {
        loadingDialog.show();

    }

    @Override
    public void hideProgress() {
        loadingDialog.hide();

    }

    @Override
    public void getSugetSSucces() {
        CartSingleton.getCart().getItems().clear();

    }
}
