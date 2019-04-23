package com.example.beijingnews.news;

import com.example.beijingnews.base.BasePresenter;
import com.example.beijingnews.model.bean.NewPagerBean;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/21 0021
 * Time: 21:38
 * Describe: ${as}
 */
public class NewsPagerPresenter extends BasePresenter<NewsPagerView> {

    public void getData(String url){
        new NewPagerModel().get(url, new NewPagerCallBack() {
            @Override
            public void successful(NewPagerBean newPagerBean) {

                getView().success(newPagerBean);
            }

            @Override
            public void failless(String ss) {

                getView().failless(ss);
            }
        });
    }
}
