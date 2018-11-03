package com.daohuyen.dell.store_cosmetics.view.detailHistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.DetailOrderAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.view.BillViewItem;
import com.daohuyen.dell.store_cosmetics.model.view.LotproductView;
import com.daohuyen.dell.store_cosmetics.presenters.detailhistory.DetailHistoryBillPresenterIpl;
import com.daohuyen.dell.store_cosmetics.presenters.detailhistory.DetailHistoryPresenter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailOrderActivity extends AppCompatActivity implements DetailView{
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_state)
    TextView txt_state;
    @BindView(R.id.txt_time)
    TextView txt_time;
    @BindView(R.id.txt_total)
    TextView txt_total;
    @BindView(R.id.rcv_detailorder)
    RecyclerView rcv_detailorder;
    DetailOrderAdapter adapter;
    List<LotproductView> list=new ArrayList<>();
    LoadingDialog loadingDialog;
    DetailHistoryPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        ButterKnife.bind(this);
        loadingDialog=new LoadingDialog(this);
        presenter=new DetailHistoryBillPresenterIpl(this,this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Chi tiết đơn hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        presenter.loadBill(getIntent().getStringExtra(Constants.ORDER_ID));
        adapter=new DetailOrderAdapter(this,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_detailorder.setLayoutManager(linearLayoutManager);
        rcv_detailorder.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        rcv_detailorder.setHasFixedSize(true);
        rcv_detailorder.setAdapter(adapter);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
    public void showDetailBill(BillViewItem billViewItem) {
        for (LotproductView lotproductView:billViewItem.getLotProducts()) {
            list.add(lotproductView);

        }
        adapter.addModels(list,false);
        if(billViewItem.getPermit()==0){
            txt_state.setText("Chưa được duyệt");
        }else {
            txt_state.setText("Đã được duyệt");

        }
        txt_name.setText(billViewItem.getReceiver());
        txt_time.setText(Utils.getTimeAndDate(billViewItem.getCreateDate()));
        txt_total.setText(Utils.formatNumberMoney(billViewItem.getTotal())+" Đ");



    }


}
