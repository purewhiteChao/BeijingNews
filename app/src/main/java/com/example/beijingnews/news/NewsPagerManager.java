package com.example.beijingnews.news;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beijingnews.R;
import com.example.beijingnews.model.bean.NewPagerBean;

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
        recyclerview = view.findViewById(R.id.recyclerview_newpager);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        return view;
    }
    public void initData(String url){

        presenter.getData(url);
    }

    @Override
    public void success(NewPagerBean data) {
        List<NewPagerBean.DataBean.TopnewsBean> topnews = data.getData().getTopnews();
        List<NewPagerBean.DataBean.NewsBean> news = data.getData().getNews();
        adapter=  new ViewpagerAdapter_NewsPagerManager(topnews);
        viewpager.setAdapter(adapter);
        adapter_recycler = new RecyclerAdapter_newspager(news);
        recyclerview.setAdapter(adapter_recycler);
//        new NewsPagerAdapter().notifyDataSetChanged();
    }

    @Override
    public void failless(String ss) {

        Log.i("GC",ss);
    }
}
