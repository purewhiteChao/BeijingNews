package com.example.beijingnews.main;

import com.example.beijingnews.model.service.RetroService;
import com.example.beijingnews.base.BaseModel;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.NewsBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    private File file;

    @Override
    public void get(String url, final MainCallBack callBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RetroService retroService = retrofit.create(RetroService.class);
        Observable<NewsBean> http = retroService.getHttp();

        http.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        callBack.failless(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean bean) {

                        callBack.successful(bean);


                    }
                });
    }

    @Override
    public void post(String url, MainCallBack callBack) {

    }

    public void getAvater(String url, String path, final MainCallBack<AvatarBean> callBack){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroService retroService = retrofit.create(RetroService.class);
        RequestBody bodyFormat = RequestBody.create(MediaType.parse("multipat/form-data"),"json");
        file = new File(path);
        RequestBody bodyFile = RequestBody.create(MediaType.parse("application/otcet-stream"),file);
        MultipartBody.Part partFlie = MultipartBody.Part.createFormData("smfile",file.getName(),bodyFile);
        Observable<AvatarBean> avatarBeanObservable = retroService.postAvater(bodyFormat, partFlie);
        avatarBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AvatarBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        callBack.failless(e.getMessage());
                    }

                    @Override
                    public void onNext(AvatarBean avatarBean) {

                        callBack.successful(avatarBean);
                    }
                });
    }
}
