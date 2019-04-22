package com.example.beijingnews.model.service;

import android.net.Uri;

import com.example.beijingnews.model.bean.NewPagerBean;
import com.example.beijingnews.model.uri.UploadURI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/21 0021
 * Time: 21:34
 * Describe: ${as}
 */
public interface NewsPagerService {
    @GET
    Call<NewPagerBean> getNewsPager(@Url String url);
}
