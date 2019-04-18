package com.example.beijingnews.model.service;

import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.NewsBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/11 0011
 * Time: 20:55
 * Describe: ${as}
 */
public interface RetroService {
    //http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
    @GET("categories.json")
    Observable<NewsBean> getHttp();

    @Headers("user-agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6756.400 QQBrowser/10.2.2443.400")
    @Multipart
    @POST("upload")
    Observable<AvatarBean> postAvater(@Part("format")RequestBody format, @Part MultipartBody.Part flie);
}
