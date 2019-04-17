package com.example.beijingnews.view.Guide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseActivity;
import com.example.beijingnews.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewpager_guide;
    private View circle_red;
    private List<ImageView> list;
    private GuiAdapter guiAdapter;
    private Button btn_jump_guide;
    private RelativeLayout relative_guide;
    private float distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_guide;
    }

    private void initListener() {
        viewpager_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
//                circle_red.setTranslationX((width / 3 / 2 - 10) * (i + v));
                circle_red.setTranslationX(distance * (i + v));
//                System.out.print(i+"--"+v+"---"+i1);
                Log.i("GC", i + "--" + v + "--" + i1);
            }

            @Override
            public void onPageSelected(int i) {

                if (i == list.size() - 1) {
                    btn_jump_guide.setVisibility(View.VISIBLE);
                } else {
                    btn_jump_guide.setVisibility(View.GONE);
                }

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
        btn_jump_guide = (Button) findViewById(R.id.btn_jump_guide);
        btn_jump_guide.setOnClickListener(this);
        relative_guide = (RelativeLayout) findViewById(R.id.relative_guide);
//        relative_guide.setOnClickListener(this);
        relative_guide.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                View childAt0 = relative_guide.getChildAt(0);
                View childAt1 = relative_guide.getChildAt(1);
                distance = childAt1.getX()-childAt0.getX();
                relative_guide.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump_guide:

                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
