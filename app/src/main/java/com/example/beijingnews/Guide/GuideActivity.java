package com.example.beijingnews.Guide;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {

    private ViewPager viewpager_guide;
    private View circle_red;
    private List<ImageView> list;
    private GuiAdapter guiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        viewpager_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                circle_red.setTranslationX((width/3/2-10)*(i+v));
//                System.out.print(i+"--"+v+"---"+i1);
                Log.i("GC",i+"--"+v+"--"+i1);
            }

            @Override
            public void onPageSelected(int i) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initData() {
         list = new ArrayList<>();
         ImageView imageView1 = new ImageView(this);
         imageView1.setImageResource(R.drawable.guide_1);
         ImageView imageView2 = new ImageView(this);
         imageView2.setImageResource(R.drawable.guide_2);
         ImageView imageView3 = new ImageView(this);
         imageView3.setImageResource(R.drawable.guide_3);
         list.add(imageView1);
         list.add(imageView2);
         list.add(imageView3);

         guiAdapter = new GuiAdapter(list);
         viewpager_guide.setAdapter(guiAdapter);
    }

    private void initView() {
        viewpager_guide = (ViewPager) findViewById(R.id.viewpager_guide);
        circle_red = (View) findViewById(R.id.circle_red);
    }
}
