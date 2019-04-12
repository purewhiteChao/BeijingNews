package com.example.beijingnews.base;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/10 0010
 * Time: 16:04
 * Describe: ${as}
 */
public abstract class BaseModel<C extends BaseCallBack> {

   public abstract void get(String url,C callBack);
   public abstract void post(String url,C callBack);
}
