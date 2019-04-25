package com.example.beijingnews.news;

import com.example.beijingnews.base.BaseView;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.NewPagerBean;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/21 0021
 * Time: 21:37
 * Describe: ${as}
 */
public interface NewsPagerView extends BaseView<NewPagerBean> {
    public void loadMore(NewPagerBean bean);

}
