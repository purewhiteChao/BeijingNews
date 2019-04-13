package com.example.beijingnews;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/12 0012
 * Time: 16:00
 * Describe: ${as}
 */
public class MyApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
