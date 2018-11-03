package com.daohuyen.dell.store_cosmetics.view.product_detail;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.ProductAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product.ProductDetailPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.shop.detail_product.ProductDetailPresenterIpl;
import com.daohuyen.dell.store_cosmetics.presenters.shop.list_product.ProductPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.shop.list_product.ProductPresenterIpl;
import com.daohuyen.dell.store_cosmetics.view.one_product_cart.ProductCartActivity;
import com.daohuyen.dell.store_cosmetics.view.product_category.Product_CategoryView;


import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity implements
        ProductDetailView,
        View.OnClickListener,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener,
        RecyclerViewAdapter.OnItemClickListener{
    @BindView(R.id.rc_apriori)
     RecyclerView rc_apriori;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.img_product_detail)
    ImageView img_productdetail;
    @BindView(R.id.img_product_background)
    ImageView img_product_background;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_name_product)
    TextView tvNameProduct;
    @BindView(R.id.tv_cost_product)
    TextView tvCostProduct;
    @BindView(R.id.tv_detail_product)
    TextView tvDescription;
    @BindView(R.id.bt_add_cart)
    Button btAddCart;
    @BindView(R.id.bt_pay)
    Button btPay;
    ProductViewModel productViewModel;
    ProductDetailPresenter presenter;
    String productID,categoryID;
List<ProductViewModel> list;
ProductAdapter adapter;
String input="18-7";
LoadingDialog loadingDialog;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        getSupportActionBar().setTitle("Các sản phẩm danh mục");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initVariables();
    }
    @Override
    public void onStart() {
        super.onStart();
        nestedScrollView.scrollTo(-1, -1);
        nestedScrollView.smoothScrollTo(0, 0);
//        if (productID != null) {
//            Toast.makeText(this, "khong tim thay san pham", Toast.LENGTH_SHORT).show();
////            presenter.firstFetchSimilarClothes(productID);
////            clothesDetailPresenter.fetchClothesDetail(getIntent().getStringExtra(Constants.KEY_CLOTHES_ID));
//        }
    }

    private void initVariables() {
        ButterKnife.bind(this);
        loadingDialog=new LoadingDialog(this);
        presenter=new ProductDetailPresenterIpl(this,this);
        nestedScrollView.scrollTo(-1, -1);
        nestedScrollView.smoothScrollTo(0, 0);
        adapter=new ProductAdapter(this);
        adapter.addOnItemClickListener(this);
        adapter.setLoadingMoreListener(this);

        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc_apriori.setLayoutManager(manager2);
        rc_apriori.setAdapter(adapter);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            actionBar.setTitle(R.string.clothes_detail);
        }
        btAddCart.setOnClickListener(this);
        btPay.setOnClickListener(this);
        productID = getIntent().getStringExtra(Constants.KEY_PRODUCT_ID);
        categoryID=getIntent().getStringExtra(Constants.CATEGORY_ID);
//        input+="-"+categoryID;

        if (productID != null) {
            presenter.fetchProductDetail(productID);
            presenter.fetchProductApriori(input);

        }
        btAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetailActivity.this, ProductCartActivity.class);
                intent.putExtra("product",productViewModel);
                startActivity(intent);
                finish();

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

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
    public void showProductDetail(ProductViewModel productViewModel) {
        this.productViewModel=productViewModel;
//        Glide.with(this).load(productViewModel.getLogoUrl()).apply(new RequestOptions().placeholder(R.drawable.background)).into(img_product_background);
        Glide.with(this).load(productViewModel.getLogoUrl()).apply(new RequestOptions().placeholder(R.drawable.logoapp)).into(img_productdetail);
        tvNameProduct.setText(productViewModel.getName());
        tvCostProduct.setText(Utils.formatNumberMoney(productViewModel.getPrice()) + " Đ");
        tvDescription.setText(productViewModel.getDes());


    }

    @Override
    public void showProductApriori(List<ProductViewModel> productViewModel) {
        adapter.addModels(productViewModel,false);

    }






}
