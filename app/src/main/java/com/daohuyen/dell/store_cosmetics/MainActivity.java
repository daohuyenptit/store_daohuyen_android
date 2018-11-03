package com.daohuyen.dell.store_cosmetics;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.common.BottomNavigationViewHelper;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.common.ViewPageAdapter;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.Profile;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.services.eventbus.HeaderProfileEvent;
import com.daohuyen.dell.store_cosmetics.view.cart.CartActivity;
import com.daohuyen.dell.store_cosmetics.view.searchproduct.SearchActivity;
import com.daohuyen.dell.store_cosmetics.view.history.HistoryBillActivity;
import com.daohuyen.dell.store_cosmetics.view.product_category.Product_CategoryView;
import com.daohuyen.dell.store_cosmetics.view.user.login.LoginActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , BottomNavigationView.OnNavigationItemSelectedListener,RecyclerViewAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener,Product_CategoryView {
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    ViewPager viewPager;
    private LoadingDialog loadingDialog;
    View userHeaderView;
    ImageView img_Search;
    ImageButton img_cart;
    TextView txt_email;
    TextView txt_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_Search=findViewById(R.id.img_search);
        img_cart=findViewById(R.id.img_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        EventBus.getDefault().register(this);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        viewPager = findViewById(R.id.view_pager);
        final ViewPageAdapter viewPageAdapter= new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.setSelectedItemId(viewPageAdapter.getMenuIDByPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        loadingDialog = new LoadingDialog(this);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        navigationView.removeHeaderView(userHeaderView);
        userHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        Profile headerProfile = Utils.getHeaderProfile(this);
        if (headerProfile.getName() != null) {
            showHeaderProfile(headerProfile);
        }
        switchNavigationDrawer(Constants.LOGIN_TRUE.equals(Utils.getSharePreferenceValues(this,Constants.STATUS_LOGIN)));

        img_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings: {
                break;
            }
            case R.id.menu_search: {
               startActivity(new Intent(MainActivity.this,SearchActivity.class));
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        //  Log.i(TAG, "onDestroy: ");
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.nav_login: {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            case R.id.nav_logout: {
                Utils.setSharePreferenceValues(this, Constants.STATUS_LOGIN, Constants.LOGIN_FAIL);
                Utils.saveHeaderProfile(this, null);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
               // EventBus.getDefault().post(new UserAuthorizationChangedEvent());
                break;
            }

            case R.id.nav_history_bill:{
                startActivity(new Intent(this, HistoryBillActivity.class));
                break;
            }
            case R.id.nav_favor:{
//                startActivity(new Intent(MainActivity.this, ListBookFavorActivity.class));
                break;
            }
            case R.id.nav_share: {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "SunRise Shop");
                    String sAux = "\nỨng dụng bán hàng online\n\n";
                    sAux = sAux + "http://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=vi \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Chọn cách thức chia sẻ"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.nav_facebook: {
                if (Utils.checkNetwork(this)) {
                    String url = "https://www.facebook.com/heart.cau.3";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else {
                    Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.nav_change_password: {
//                if (Utils.checkNetwork(this)) {
//                    startActivity(new Intent(this, ChangePasswordActivity.class));
//                } else {
//                    Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
//                }
//                break;
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void switchNavigationDrawer(boolean isLoggedIn) {
        navigationView.getMenu().clear();
        navigationView.removeHeaderView(userHeaderView);
        if (isLoggedIn) {
            userHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_auth);
            navigationView.inflateMenu(R.menu.menu_navigation_login);
            Utils.setSharePreferenceValues(this, Constants.STATUS_LOGIN, Constants.LOGIN_TRUE);
            txt_email=findViewById(R.id.txt_email_login);
            txt_name=findViewById(R.id.txt_full_name_login);
            CustomerView customerView=Utils.getCustomerview(this);
//            txt_name.setText("1");
//            txt_email.setText("1");
        } else {
            userHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_no_auth);
            navigationView.inflateMenu(R.menu.menu_navigation_logout);
            Utils.setSharePreferenceValues(this, Constants.STATUS_LOGIN, Constants.LOGIN_FAIL);
        }
    }
    public void showHeaderProfile(Profile headerProfile) {
        ImageView imageView = userHeaderView.findViewById(R.id.img_avatar);
        TextView txtFullname = userHeaderView.findViewById(R.id.txt_full_name);
        TextView txtEmail = userHeaderView.findViewById(R.id.txt_email);
        if (headerProfile != null) {
            Glide.with(this)
                    .load(headerProfile.getSrcAvatar())
                    .apply(new RequestOptions().placeholder(R.drawable.avatar_placeholder).error(R.drawable.avatar_placeholder))
                    .into(imageView);
            txtEmail.setText(headerProfile.getUser().getUsername());
            txtFullname.setText(headerProfile.getName());
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivedHeaderUpdateEvent(HeaderProfileEvent headerProfileEvent) {
        Utils.saveHeaderProfile(MainActivity.this, headerProfileEvent.getHeaderProfile());
        showHeaderProfile(headerProfileEvent.getHeaderProfile());
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void hideLoadMoreProgress() {

    }

    @Override
    public void enableLoadMore(boolean enable) {

    }

    @Override
    public void enableRefreshing(boolean enable) {

    }

    @Override
    public void showRefreshingProgress() {

    }

    @Override
    public void hideRefreshingProgress() {

    }

    @Override
    public void addProductPreviews(PageList<ProductViewModel> productViewModelPageList) {

    }

    @Override
    public void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList) {

    }
}
