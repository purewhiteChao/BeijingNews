package com.example.beijingnews.base;

import android.view.View;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/10 0010
 * Time: 16:04
 * Describe: ${as}
 */
public abstract class BasePresenter<V extends BaseView> {
    private V view;

    public void attachView(V view){
        this.view = view;
    }
    public void detachView(){
        this.view =null;
    }
    public V getView(){
        return view;
    }
    public boolean isView(){
        return view!=null;
    }

}
