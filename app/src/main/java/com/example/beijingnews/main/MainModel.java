package com.example.beijingnews.main;

import com.example.beijingnews.model.RetroService;
import com.example.beijingnews.base.BaseModel;
import com.example.beijingnews.bean.Bean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/11 0011
 * Time: 20:39
 * Describe: ${as}
 */
public class MainModel extends BaseModel<MainCallBack> {
    @Override
    public void get(String url, final MainCallBack callBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RetroService retroService = retrofit.create(RetroService.class);
        Observable<Bean> http = retroService.getHttp("1", "20", "1");

        http.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        callBack.failless(e.getMessage());
                    }

                    @Override
                    public void onNext(Bean bean) {

                        callBack.successful(bean);

                    }
                });
    }

    @Override
    public void post(String url, MainCallBack callBack) {

    }
}
