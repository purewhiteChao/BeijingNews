package com.example.beijingnews.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseFragment;
import com.example.beijingnews.main.MainActivity;
import com.example.beijingnews.model.bean.NewsBean;
import com.example.beijingnews.news.NewsPagerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/18 0018
 * Time: 17:25
 * Describe: ${as}
 */
public class NewsFragment extends BaseFragment {


    @BindView(R.id.tablayout_newsfragment)
    TabLayout tablayoutNewsfragment;
    @BindView(R.id.viewpager_newsfragment)
    ViewPager viewpagerNewsfragment;
    private NewsPagerAdapter adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        if(activity.data1!=null){
            List<NewsBean.DataBean.ChildrenBean> children1 = activity.data1.get(0).getChildren();
            adapter = new NewsPagerAdapter(children1);
            viewpagerNewsfragment.setAdapter(adapter);
        }



    }

    /**
     * TODO----------------------------EventBusSubcribe------------------------------------
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEventBus(List<NewsBean.DataBean.ChildrenBean> children){
        adapter = new NewsPagerAdapter(children);
        viewpagerNewsfragment.setAdapter(adapter);
    }

    /**
     * TODO---------------------------getContextView------------------------------------
     */
    @Override
    public View getContextView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.newsfragment, container, false);
    }
}
