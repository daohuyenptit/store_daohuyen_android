package com.daohuyen.dell.store_cosmetics.view.fragment.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.adapter.CateAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.CategoryAdaper;
import com.daohuyen.dell.store_cosmetics.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.store_cosmetics.adapter.SliderAdapter;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.Cart;
import com.daohuyen.dell.store_cosmetics.model.response.Advertisement;
import com.daohuyen.dell.store_cosmetics.model.view.CategoryViewModel;
import com.daohuyen.dell.store_cosmetics.presenters.shop.list_category.CategoryPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.shop.list_category.CategoryPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements HomeFragmentView{
    @BindView(R.id.recycler_category_one)
    RecyclerView recycler_category_one;
    @BindView(R.id.recycler_category_two)
    RecyclerView recycler_category_two;
    @BindView(R.id.recycler_category_three)
    RecyclerView recycler_category_three;
    CategoryAdaper adapter1;
    CategoryAdaper adapter2;
    CategoryAdaper adapter3;
    List<CategoryViewModel> categories;
    CategoryPresenter presenter;
    LoadingDialog loadingDialog;


    @BindView(R.id.viewpager_slider)
    ViewPager viewPagersSlider;
    @BindView(R.id.indicator)
    TabLayout indicator;
    private SliderAdapter sliderAdapter;
    List<Advertisement> advertisements;
    Timer timer;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        adapter1=new CategoryAdaper(getContext(),false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_category_one.setLayoutManager(manager1);
        recycler_category_one.setAdapter(adapter1);
        adapter2=new CategoryAdaper(getContext(),false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_category_two.setLayoutManager(manager2);
        recycler_category_two.setAdapter(adapter2);
        adapter3=new CategoryAdaper(getContext(),false);
        LinearLayoutManager manager3 = new LinearLayoutManager(getActivity());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_category_three.setLayoutManager(manager3);
        recycler_category_three.setAdapter(adapter3);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }
    public void initData(){
        loadingDialog=new LoadingDialog(getContext());
        presenter = new CategoryPresenterImpl(getActivity(),this);

        advertisements= new ArrayList<>();
        advertisements.add(new Advertisement("1","https://d10qoa1dy3vloz.cloudfront.net/resized/840x/slots-img/bra/brandlanding_jouer_new_840x400-34lqh.jpg","https://myphambo.com/my-pham-trang-diem/"));
        advertisements.add(new Advertisement("1","http://www.eurosalesinternational.ie/wp-content/uploads/2016/10/Note.png","https://myphambo.com/shop/ba%CC%89ng-mau-mat-sivanna-colors-luxury-velvet-eyesh/"));
        advertisements.add(new Advertisement("1","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0vE_zfbSVDAZQxnwYP7tCvfj0G7hOXlXvH95ViRQFU8D6nqX1","https://myphambo.com/ban-buon-pham-han-quoc/"));
        advertisements.add(new Advertisement("1","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwTGV3Ih5wVoVNVJFm42OEn5ez8VuxRK61NMxaA1M-gyONiF4d","https://myphambo.com/ban-buon-pham-han-quoc/"));
        sliderAdapter= new SliderAdapter(getContext(), advertisements);
        viewPagersSlider.setAdapter(sliderAdapter);
        indicator.setupWithViewPager(viewPagersSlider, true);
        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        presenter.loadAllCategory();
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
    public void loadAllCategories(List<CategoryViewModel> list) {
        categories= list;
        ArrayList<CategoryViewModel> list1=new ArrayList<>();
        ArrayList<CategoryViewModel> list2= new ArrayList<>();
        ArrayList<CategoryViewModel> list3= new ArrayList<>();
        for (CategoryViewModel categoryViewModel: categories) {
            if(categoryViewModel.getIdPL().equals("1")){
                list1.add(categoryViewModel);
            }else if(categoryViewModel.getIdPL().equals("2")){
                list2.add(categoryViewModel);
            }else if(categoryViewModel.getIdPL().equals("3")){
                list3.add(categoryViewModel);
            }
        }
        adapter1.addModels(list1,false);
        adapter2.addModels(list2,false);
        adapter3.addModels(list3,false);
    }



    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPagersSlider.getCurrentItem() < advertisements.size() - 1) {
                        viewPagersSlider.setCurrentItem(viewPagersSlider.getCurrentItem() + 1);
                    } else {
                        viewPagersSlider.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }
}
