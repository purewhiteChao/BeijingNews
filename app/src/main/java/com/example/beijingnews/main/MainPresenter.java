package com.example.beijingnews.main;

import com.example.beijingnews.base.BasePresenter;
import com.example.beijingnews.base.BaseView;
import com.example.beijingnews.bean.Bean;
import com.example.beijingnews.model.ModelManager;

import java.lang.reflect.Constructor;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/11 0011
 * Time: 20:38
 * Describe: ${as}
 */
public class MainPresenter extends BasePresenter<MainView> {
    public void getData(String url){
        ModelManager.getInstance().getModel(MainModel.class).get(url, new MainCallBack() {
            @Override
            public void successful(Bean bean) {

                getView().success(bean);
            }

            @Override
            public void failless(String ss) {

                getView().failless(ss);
            }
        });

    }
}
