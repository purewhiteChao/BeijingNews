package com.example.beijingnews.news;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.beijingnews.R;
import com.example.beijingnews.model.bean.NewPagerBean;
import com.example.beijingnews.model.uri.UploadURI;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/21 0021
 * Time: 21:09
 * Describe: ${as}
 */
public class ViewpagerAdapter_NewsPagerManager extends PagerAdapter {
    private List<NewPagerBean.DataBean.TopnewsBean> list;
    public ViewpagerAdapter_NewsPagerManager(List<NewPagerBean.DataBean.TopnewsBean> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_newspager,container,false);
        ImageView imageView = view.findViewById(R.id.imageview_viewpager_newspager);
        TextView textView = view.findViewById(R.id.text_viewpager_newpager);
        textView.setText(list.get(position).getTitle());
        //http://10.0.2.2:8080/zhbj/10006/1452327318UU91.jpg
        String substring = list.get(position).getTopimage().substring(15);
        Log.i("GC",substring);
        Glide.with(container.getContext()).load(UploadURI.dowmPicture +substring).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
