package com.daohuyen.dell.store_cosmetics.view.confirm_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.PayAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.model.Pay;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener,View.OnClickListener{
    @BindView(R.id.rc_pay)
    RecyclerView mRecycleView;
    PayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Phương thức thanh toán");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        adapter=new PayAdapter(this,false);
        ArrayList<Pay> list=new ArrayList<>();
        list.add(new Pay("Thẻ tín dụng/Ghi nợ",R.drawable.ic_the));
        list.add(new Pay("Thanh toán khi nhận hàng",R.drawable.ic_money));
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
        Pay pay=this.adapter.getItem(position,Pay.class);
        Intent intent=new Intent();
        intent.putExtra(Constants.PAY,pay);
        setResult(Constants.RESULT_CODE_PAY,intent);
        finish();

    }

    @Override
    public void onClick(View v) {

    }
}
