package com.example.beijingnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.beijingnews.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/12 0012
 * Time: 15:56
 * Describe: ${as}
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContextView());
        ButterKnife.bind(this);
        context = MyApp.getContext();
    }
    protected abstract int getContextView();
}
