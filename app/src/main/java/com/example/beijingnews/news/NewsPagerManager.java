package com.example.beijingnews.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beijingnews.R;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.NewPagerBean;
import com.example.beijingnews.view.customview.MyRecyclerView;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/21 0021
 * Time: 21:02
 * Describe: ${as}
 */
public class NewsPagerManager implements NewsPagerView {
    private View view;
    private ViewGroup viewgroup;
    private Context context;
    private ViewPager viewpager;
    private RecyclerView recyclerview;
    private int position;
    private ViewpagerAdapter_NewsPagerManager adapter;
    private NewsPagerPresenter presenter;
    private RecyclerAdapter_newspager adapter_recycler;
    private SwipeRefreshLayout refreshLayout;
    private AppBarLayout appbar;
    private MyRecyclerView recyclerView;
    private LinearLayoutManager manager;
    private MyRecyclerView myRecyclerView;
    private String loadMore;

    public NewsPagerManager(ViewGroup container,int position){
        this.viewgroup = container;
        this.position = position;
        presenter = new NewsPagerPresenter();
        presenter.attachView(this);
    }

    public View initView(){
        context = viewgroup.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.news_pager,viewgroup,false);
        viewpager = view.findViewById(R.id.viewpager_newspager);
        refreshLayout = view.findViewById(R.id.refreshlayout);
        recyclerview = view.findViewById(R.id.recyclerview_newpager);
        appbar = view.findViewById(R.id.appbar_newspager);
        manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        return view;
    }
    public void initData(final String url){

        presenter.getData(url,true);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData(url,true);
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(i>=0){
                    refreshLayout.setEnabled(true);
                }else{
                    refreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void success(NewPagerBean data) {
        refreshLayout.setRefreshing(false);
        loadMore = data.getData().getMore().substring(1);
        List<NewPagerBean.DataBean.TopnewsBean> topnews = data.getData().getTopnews();
        List<NewPagerBean.DataBean.NewsBean> news = data.getData().getNews();
        adapter=  new ViewpagerAdapter_NewsPagerManager(topnews);
        viewpager.setAdapter(adapter);
        adapter_recycler = new RecyclerAdapter_newspager(news);
        myRecyclerView = new MyRecyclerView(adapter_recycler);
        recyclerview.setAdapter(myRecyclerView);

        initListener();
    }

    private void initListener() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
                if(lastCompletelyVisibleItemPosition == myRecyclerView.getItemCount()-2){
                    myRecyclerView.setFooterStatus(MyRecyclerView.LOADING);
                    if(!TextUtils.isEmpty(loadMore)){
                        presenter.getData(loadMore,false);
                    }else{
                        myRecyclerView.setFooterStatus(MyRecyclerView.LOAD_FAIL);
                    }

                }
            }
        });
    }

    @Override
    public void failless(String ss) {

        Log.i("GC",ss);
    }

    @Override
    public void loadMore(NewPagerBean bean) {
        loadMore = bean.getData().getMore();
       myRecyclerView.setFooterStatus(MyRecyclerView.LOADED);
        List<NewPagerBean.DataBean.NewsBean> news = bean.getData().getNews();
        adapter_recycler.refresh(news);


    }
}
