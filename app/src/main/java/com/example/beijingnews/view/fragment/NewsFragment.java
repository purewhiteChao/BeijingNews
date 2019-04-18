package com.example.beijingnews.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseFragment;
import com.example.beijingnews.main.MainActivity;
import com.example.beijingnews.model.bean.NewsBean;
import com.example.beijingnews.view.NewsPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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


    /**
     * TODO---------------------------getContextView------------------------------------
     */
    @Override
    public View getContextView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.newsfragment, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        List<NewsBean.DataBean.ChildrenBean> children = activity.data1.get(0).getChildren();
        adapter = new NewsPagerAdapter(children);
        viewpagerNewsfragment.setAdapter(adapter);
    }
}
