package com.example.beijingnews.view.Guide;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/12 0012
 * Time: 19:27
 * Describe: ${as}
 */
public class GuiAdapter extends PagerAdapter {

    private List<ImageView> list = new ArrayList<>();
    public GuiAdapter(List<ImageView> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));
    }
}
