package com.daohuyen.dell.store_cosmetics.view.history;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.HistoryAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.view.BillView;
import com.daohuyen.dell.store_cosmetics.presenters.historybill.HistoryBillPresenterIpl;
import com.daohuyen.dell.store_cosmetics.presenters.historybill.HistoryPresenter;
import com.daohuyen.dell.store_cosmetics.view.detailHistory.DetailOrderActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryBillActivity extends AppCompatActivity implements HistoryBillView,RecyclerViewAdapter.OnItemClickListener{
    @BindView(R.id.rcv_historybill)
    RecyclerView rcv_historybill;
    HistoryPresenter presenter;
    HistoryAdapter adapter1;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_bill);
        ButterKnife.bind(this);
        loadingDialog=new LoadingDialog(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Thông tin đơn hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter1=new HistoryAdapter(this,false);
        adapter1.addOnItemClickListener(this);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_historybill.setLayoutManager(manager1);
        rcv_historybill.setAdapter(adapter1);
        presenter=new HistoryBillPresenterIpl(this,this);
        if(Utils.getSharePreferenceValues(this,Constants.STATUS_LOGIN).equals(Constants.LOGIN_TRUE)){
            presenter.loadAllBills(Utils.getSharePreferenceValues(this , Constants.CUSTOMER_ID));

        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showLoading() {
        loadingDialog.show();

    }

    @Override
    public void hideLoading() {
        loadingDialog.hide();

    }

    @Override
    public void loadAllHistoryBills(List<BillView> list) {
        adapter1.addModels(list,false);

    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        BillView billView=this.adapter1.getItem(position,BillView.class);
        Intent intent=new Intent(HistoryBillActivity.this, DetailOrderActivity.class);
        intent.putExtra(Constants.ORDER_ID,billView.getId());
        startActivity(intent);

    }
}
