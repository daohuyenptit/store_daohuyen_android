package com.daohuyen.dell.store_cosmetics.view.one_product_cart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.CartSingleton;
import com.daohuyen.dell.store_cosmetics.model.Item;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.view.bill.BillActivity;
import com.daohuyen.dell.store_cosmetics.view.cart.CartActivity;
import com.daohuyen.dell.store_cosmetics.view.user.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCartActivity extends AppCompatActivity implements View.OnClickListener{
    public static String TAG = "ProductCartActivity";

    @BindView(R.id.img_product_detail)
    ImageView img_productdetail;
    @BindView(R.id.tv_name_product)
    TextView tv_name_product;
    @BindView(R.id.tv_cost_product)
    TextView tv_cost_product;
    @BindView(R.id.txt_number)
    TextView txt_number;
    @BindView(R.id.bt_them)
    Button bt_them;
    @BindView(R.id.img_add)
    ImageButton img_add;
    @BindView(R.id.img_sub)
    ImageButton img_sub;
    ProductViewModel productViewModel;
    int count=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Lựa chọn sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        productViewModel= (ProductViewModel) getIntent().getSerializableExtra("product");
        Log.d(TAG,productViewModel.getName());
        Log.d(TAG,productViewModel.getLogoUrl());
        Log.d(TAG,productViewModel.getPrice()+"");
        Glide.with(this).load(productViewModel.getLogoUrl())
                .apply(new RequestOptions().placeholder(R.drawable.logoapp))
                .into(img_productdetail);
        tv_name_product.setText(productViewModel.getName());
        tv_cost_product.setText(Utils.formatNumberMoney(productViewModel.getPrice())+"Đ");
        txt_number.setText(count+"");

        img_add.setOnClickListener(this);
        img_sub.setOnClickListener(this);
        bt_them.setOnClickListener(this);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_CODE_ORDER: {
                if (Activity.RESULT_OK == resultCode) {
                    startActivity(new Intent(ProductCartActivity.this, CartActivity.class));
                    finish();
                }
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_them:
                if(Constants.LOGIN_TRUE.equals(Utils.getSharePreferenceValues(getApplicationContext(), Constants.STATUS_LOGIN))) {
                Item item=new Item(productViewModel,Integer.parseInt(txt_number.getText().toString()));
                CartSingleton.getCart().addItem(item);
                startActivity(new Intent(ProductCartActivity.this, CartActivity.class));
                finish();
                }else{

                    startActivityForResult(new Intent(ProductCartActivity.this, LoginActivity.class), Constants.REQUEST_CODE_ORDER);
                }

                break;
            case R.id.img_add:
                count++;
                txt_number.setText(count+"");
                break;
            case R.id.img_sub:
                if(count>1){
                    count--;
                    txt_number.setText(count+"");
                }
                break;


        }

    }
}
