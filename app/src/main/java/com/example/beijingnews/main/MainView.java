package com.example.beijingnews.main;

import com.example.beijingnews.base.BaseView;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.Bean;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/11 0011
 * Time: 20:39
 * Describe: ${as}
 */
public interface MainView extends BaseView<Bean> {
    public void getAvater(AvatarBean bean);
}
