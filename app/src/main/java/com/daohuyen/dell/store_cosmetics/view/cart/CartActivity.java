package com.daohuyen.dell.store_cosmetics.view.cart;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
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
import com.daohuyen.dell.store_cosmetics.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.ProductAdapter;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.body.BillBody;
import com.daohuyen.dell.store_cosmetics.model.body.LotProductBody;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.cart.AprioriPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.cart.AprioriPresenterIpl;
import com.daohuyen.dell.store_cosmetics.view.bill.BillActivity;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.CartAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.CartSingleton;
import com.daohuyen.dell.store_cosmetics.model.Item;
import com.daohuyen.dell.store_cosmetics.view.confirm_order.ConfirmActivity;
import com.daohuyen.dell.store_cosmetics.view.product_detail.ProductDetailActivity;
import com.daohuyen.dell.store_cosmetics.view.user.login.LoginActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener,CartView,
        View.OnClickListener,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener,
        CartAdapter.ClickChangeCount {
    @BindView(R.id.rcv_cart)
    RecyclerView mRecycleView;
    @BindView(R.id.rc_apriori)
    RecyclerView rc_apriori;
    ArrayList<Item> items;
    CartAdapter adapter;
    @BindView(R.id.bt_pay)
    Button bt_pay;
    @BindView(R.id.img_empty)
    ImageView img_empty;
     @BindView(R.id.txt_apriori)
     TextView txt_apriori;
    List<ProductViewModel> list;
    ProductAdapter adapterp;
    AprioriPresenter presenter;
    StringBuffer input=new StringBuffer();
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        loadingDialog=new LoadingDialog(this);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Giỏ hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        presenter =new AprioriPresenterIpl(this,this);
        //adapter apriori
        adapterp=new ProductAdapter(this);
        adapterp.addOnItemClickListener(this);
        adapterp.setLoadingMoreListener(this);
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc_apriori.setLayoutManager(manager2);
        rc_apriori.setAdapter(adapterp);

        //adapter cart
        adapter=new CartAdapter(this,this);
        adapter.addModels(CartSingleton.getCart().getItems(),false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);
        adapter.removeOnItemClickListener(this);
        adapter.setSelectedMode(true);

        ArrayList<Integer> list=new ArrayList<>();

            for (int i = 0; i <CartSingleton.getCart().getItems().size() ; i++) {
                if(list.size()!=0 &&!list.contains(Integer.parseInt(CartSingleton.getCart().getItems().get(i).getProduct().getCategoryID()))){
                    list.add(Integer.parseInt(CartSingleton.getCart().getItems().get(i).getProduct().getCategoryID()));
                }else if(list.size()==0){
                    list.add(Integer.parseInt(CartSingleton.getCart().getItems().get(i).getProduct().getCategoryID()));
                }

                Collections.sort(list);

            }
            for (int i=0;i<list.size();i++){
                input.append(list.get(i)).append("-");
            }
//            if(input.length()>1){
//                input.substring(0,input.length()-1);
//
//            }
            if (input.length()>1) {
                presenter.fetchProductApriori(input.toString().substring(0,input.length()-1));
                bt_pay.setVisibility(View.VISIBLE);
                txt_apriori.setVisibility(View.VISIBLE);
                img_empty.setVisibility(View.INVISIBLE);



            }else{
                bt_pay.setVisibility(View.INVISIBLE);
                txt_apriori.setVisibility(View.INVISIBLE);
                img_empty.setVisibility(View.VISIBLE);
            }
            bt_pay.setOnClickListener(this);

        }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        ProductViewModel productViewModel = this.adapterp.getItem(position, ProductViewModel.class);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(Constants.KEY_PRODUCT_ID, productViewModel.getId());
        intent.putExtra(Constants.CATEGORY_ID,productViewModel.getCategoryID());
        startActivity(intent);
        finish();
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
    public void showProductApriori(List<ProductViewModel> productViewModel) {
        adapterp.addModels(productViewModel,false);

    }


            @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_pay:
                startActivity(new Intent(CartActivity.this, ConfirmActivity.class));
                finish();
                break;
        }

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void clickAddCount(int possition) {
        CartSingleton.getCart().plusToCart(adapter.getItem(possition, Item.class));
        adapter.notifyItemChanged(possition);
    }

    @Override
    public void clickSubCount(int possition) {
        CartSingleton.getCart().subToCart(adapter.getItem(possition, Item.class));
        adapter.notifyItemChanged(possition);
    }
}
