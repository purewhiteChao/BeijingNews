package com.example.beijingnews.main;

import com.example.beijingnews.base.BasePresenter;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.ModelManager;
import com.example.beijingnews.model.bean.NewsBean;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/11 0011
 * Time: 20:38
 * Describe: ${as}
 */
public class MainPresenter extends BasePresenter<MainView> {
    public void getAvaterData(String url, String path) {
        ModelManager.getInstance().getModel(MainModel.class).getAvater(url, path, new MainCallBack<AvatarBean>() {
            @Override
            public void successful(AvatarBean avatarBean) {
                getView().getAvater(avatarBean);
            }

            @Override
            public void failless(String ss) {

                getView().failless("Avater:"+ss);
            }
        });

    }
    public void getNewsData(String url){

        ModelManager.getInstance().getModel(MainModel.class).get(url, new MainCallBack<NewsBean>() {

            @Override
            public void successful(NewsBean newsBean) {
                getView().success(newsBean);
            }

            @Override
            public void failless(String ss) {

                getView().failless("News:"+ss);
            }
        });
    }
}
