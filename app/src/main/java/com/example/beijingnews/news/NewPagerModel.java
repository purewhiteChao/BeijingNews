package com.example.beijingnews.news;

import android.util.Log;

import com.example.beijingnews.base.BaseModel;
import com.example.beijingnews.model.bean.NewPagerBean;
import com.example.beijingnews.model.service.NewsPagerService;
import com.example.beijingnews.model.uri.UploadURI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/21 0021
 * Time: 21:39
 * Describe: ${as}
 */
public class NewPagerModel extends BaseModel<NewPagerCallBack> {
    @Override
    public void get(String url, final NewPagerCallBack callBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UploadURI.localZHBJ)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsPagerService newsPagerService = retrofit.create(NewsPagerService.class);
        Call<NewPagerBean> newsPager = newsPagerService.getNewsPager(url);
        Log.i("GC","Path:"+url);
        newsPager.enqueue(new Callback<NewPagerBean>() {
            @Override
            public void onResponse(Call<NewPagerBean> call, Response<NewPagerBean> response) {
                if(response.isSuccessful()){
                    callBack.successful(response.body());
                }
                Log.i("GC",response.code()+"");
            }

            @Override
            public void onFailure(Call<NewPagerBean> call, Throwable t) {

                callBack.failless(t.getMessage());
            }
        });
    }

    @Override
    public void post(String url, NewPagerCallBack callBack) {

    }
}
