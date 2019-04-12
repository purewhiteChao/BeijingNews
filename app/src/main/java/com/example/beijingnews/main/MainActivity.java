package com.example.beijingnews.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseActivity;
import com.example.beijingnews.bean.Bean;

public class MainActivity extends BaseActivity<MainPresenter,MainView, Bean> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.getData("http://www.qubaobei.com/ios/cf/");

    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void success(Bean data) {

        Log.i("GC",data.getData().get(0).getTitle());
    }

    @Override
    public void failless(String ss) {

        Log.i("GC",ss);
    }
}
