package com.example.beijingnews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/10 0010
 * Time: 16:03
 * Describe: ${as}
 */
public abstract class MVPActivity<P extends BasePresenter,V extends BaseView,D> extends BaseActivity implements BaseView<D> {
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        presenter.attachView((V)this);
    }
    public abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
