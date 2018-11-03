package com.daohuyen.dell.store_cosmetics.view.confirm_order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.model.Address;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_province)
    EditText et_province;
    @BindView(R.id.et_village)
    EditText et_village;
    @BindView(R.id.et_town)
    EditText et_town;
    @BindView(R.id.et_house)
    EditText et_house;
    @BindView(R.id.bt_choice)
    Button bt_choice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Chỉnh sửa địa chỉ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        bt_choice.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String addr=et_house.getText().toString()+","+et_village.getText().toString()+","+et_village.getText().toString()+","+et_province.getText().toString();
        Address address=new Address(et_name.getText().toString(),et_phone.getText().toString(),addr);
        Intent intent=new Intent();
        intent.putExtra(Constants.ADDRESS,address);
        setResult(Constants.RESULT_CODE_ADDRESS,intent);
        finish();

    }
}
