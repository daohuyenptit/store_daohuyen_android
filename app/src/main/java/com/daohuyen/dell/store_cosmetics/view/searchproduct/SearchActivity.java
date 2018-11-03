package com.daohuyen.dell.store_cosmetics.view.searchproduct;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;


import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.ProductAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.model.PageList;
import com.daohuyen.dell.store_cosmetics.model.view.ProductViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.searchproduct.SearchPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.searchproduct.SearchPresenterIpl;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchProductView,
 RecyclerViewAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener, android.widget.SearchView.OnQueryTextListener {
        @BindView(R.id.rc_posts)
    RecyclerView mRecycleView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.search)
    SearchView searchView;
    ProductAdapter adapter;
    SearchPresenter presenter;
    List<ProductViewModel> productViewModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        presenter = new SearchPresenterIpl(this, this);
//        setHasOptionsMenu(true);// tạo menu
        adapter = new ProductAdapter(this);
        adapter.addOnItemClickListener(this);
        adapter.setLoadingMoreListener(this);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryLight, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);
        searchView.setOnQueryTextListener(this);

        presenter.refreshProductPreviews();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//
//
//
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//
//        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
//        search = (android.support.v7.widget.SearchProductView) searchItem.getActionView();
//        search.setOnQueryTextListener(new android.support.v7.widget.SearchProductView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(newText.isEmpty()){
//                    adapter.clear();
//                    adapter.addModels(productViewModels,false);
//                }else{
//                    enableRefreshing(false);
//                    load(newText);
//                }
//                return false;
//            }
//        });
//        searchProductView.setQueryHint(getString(R.string.search));
//        return true;
////        return super.onCreateOptionsMenu(menu);
//    }
    private void load(String text) {
        List<ProductViewModel> ls = new ArrayList<>();

            for (int i = 0; i < productViewModels.size(); i++) {
                String aux_name = format(productViewModels.get(i).getName());
                String aux_text = format(text);
                boolean found = aux_name.replaceAll(" ", "").toUpperCase().contains(aux_text.replaceAll(" ", "").toUpperCase());
                if (found) {
                    ls.add(productViewModels.get(i));
                }
            }
            adapter.clear();
            adapter.addModels(ls, false);
            adapter.notifyDataSetChanged();

    }
    public static String format(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    @Override
    public void showLoadMoreProgress() {
        adapter.showLoadingItem(true);

    }

    @Override
    public void hideLoadMoreProgress() {

            adapter.hideLoadingItem();

    }

    @Override
    public void enableLoadMore(boolean enable) {
        adapter.enableLoadingMore(enable);

    }

    @Override
    public void enableRefreshing(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);

    }

    @Override
    public void showRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void addProductPreviews(PageList<ProductViewModel> productViewModelPageList) {
        adapter.addModels(productViewModelPageList.getResults(),false);

    }

    @Override
    public void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList) {
        adapter.refresh(productViewModelPageList.getResults());
        this.productViewModels = productViewModelPageList.getResults();

    }


    @Override
    public void onRefresh() {
        presenter.refreshProductPreviews();

    }

    @Override
    public void onLoadMore() {
        presenter.loadMoreProductPreviews();

    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.isEmpty()){
            adapter.clear();
            adapter.addModels(productViewModels,false);
        }else{
            enableRefreshing(false);
            load(newText);
        }
        return false;
    }
}
