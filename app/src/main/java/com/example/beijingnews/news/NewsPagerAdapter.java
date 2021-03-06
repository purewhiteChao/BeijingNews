package com.example.beijingnews.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.beijingnews.model.bean.NewsBean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/18 0018
 * Time: 21:16
 * Describe: ${as}
 */
public class NewsPagerAdapter extends PagerAdapter {
    private List<NewsBean.DataBean.ChildrenBean> list_Str;
    private NewsPagerManager manager;

    public NewsPagerAdapter(List<NewsBean.DataBean.ChildrenBean> list_Str){
        this.list_Str = list_Str;
    }
    @Override
    public int getCount() {
        return list_Str.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Str.get(position).getTitle();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        manager = new NewsPagerManager(container,position);
        String url = list_Str.get(position).getUrl().substring(1);
        Log.i("GC","url:"+url);
        View view = manager.initView();
        manager.initData(url);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
