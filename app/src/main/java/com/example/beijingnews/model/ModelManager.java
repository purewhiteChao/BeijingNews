package com.example.beijingnews.model;

import com.example.beijingnews.RetroService;
import com.example.beijingnews.bean.Bean;
import com.example.beijingnews.main.MainModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

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
 * Time: 20:54
 * Describe: ${as}
 */
public class ModelManager {
    private static final ModelManager ourInstance = new ModelManager();
//    private MainModel mainModel = new MainModel();

    public static ModelManager getInstance() {
        return ourInstance;
    }

    private ModelManager() {
    }

    public<M> M getModel(Class<M> modelClass){
        try {
            Constructor<M> constructor = modelClass.getDeclaredConstructor();
            M m = constructor.newInstance();
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
