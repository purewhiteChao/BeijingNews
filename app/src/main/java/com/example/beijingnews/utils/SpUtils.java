package com.example.beijingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.beijingnews.MyApp;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/18 0018
 * Time: 16:31
 * Describe: ${as}
 */
public class SpUtils {

    private static SharedPreferences sp = MyApp.getContext().getSharedPreferences("beijingnews", Context.MODE_PRIVATE);

    public static <T>void setSp(String key,T data){
        SharedPreferences.Editor edit = sp.edit();
        if (data instanceof String) {
            edit.putString(key, (String) data);
        }else if(data instanceof Integer){
            edit.putInt(key, (Integer) data);
        }else if(data instanceof Boolean){
            edit.putBoolean(key, (Boolean) data);
        }
        edit.commit();
    }
//    public static <T>T getSp(String key,T defValue){
//        if(defValue instanceof String){
//            return (T) sp.getString(key, (String) defValue);
//        }else if(defValue instanceof Integer){
//            return (T) sp.getInt(key,(Integer) defValue);
//        }else if(defValue instanceof Boolean){
//            return (T) sp.getBoolean(key,(Boolean)defValue);
//        }
//    }

    public static String getString(String key,String defValue){
        return sp.getString(key,defValue);
    }
    public static boolean getBoolean(String key, boolean defValue){
        return sp.getBoolean(key,defValue);
    }
    public static int getInt(String key,int defValue){
        return sp.getInt(key,defValue);
    }
}
