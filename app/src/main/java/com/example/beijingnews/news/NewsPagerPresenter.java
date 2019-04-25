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

    private boolean flag;
    public void getData(String url, final boolean flag){
        this.flag = flag;
        new NewPagerModel().get(url, new NewPagerCallBack() {
            @Override
            public void successful(NewPagerBean newPagerBean) {

                if(flag){
                    getView().success(newPagerBean);
                }else{
                    getView().loadMore(newPagerBean);
                }
            }

            @Override
            public void failless(String ss) {

                getView().failless(ss);
            }
        });
    }
}
