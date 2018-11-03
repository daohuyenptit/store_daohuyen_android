package com.daohuyen.dell.store_cosmetics.view.confirm_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.TransportAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.model.CartSingleton;
import com.daohuyen.dell.store_cosmetics.model.Transport;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransportActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener, View.OnClickListener{
    @BindView(R.id.rc_transport)
    RecyclerView mRecycleView;
    ArrayList<Transport> list=new ArrayList<>();
    TransportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Đơn vị vận chuyển");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        list.add(new Transport("Giao hàng siêu nhanh",40000,"Giao hàng trong 1-2 ngày"));
        list.add(new Transport("Giao hàng siêu tiết kiệm",15000,"Giao hàng trong 5-7 ngày"));
        list.add(new Transport("Giao hàng nhanh",30000,"Giao hàng trong 2-3 ngày"));
        list.add(new Transport("Giao hàng tiết kiệm",40000,"Giao hàng trong 3-5 ngày"));
        adapter=new TransportAdapter(this,false);
        adapter.addModels(list,false);
        adapter.addOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        Transport transport=this.adapter.getItem(position,Transport.class);
        Intent intent=new Intent();
        intent.putExtra(Constants.TRANSPORT,transport);
        setResult(Constants.RESULT_CODE_TRANSPORT,intent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
