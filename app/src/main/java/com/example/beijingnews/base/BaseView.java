package com.example.beijingnews.base;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/10 0010
 * Time: 16:04
 * Describe: ${as}
 */
public interface BaseView<D> {
    void success(D data);
    void failless(String ss);
}
