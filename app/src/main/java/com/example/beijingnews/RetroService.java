package com.example.beijingnews;

import com.example.beijingnews.bean.Bean;

import retrofit2.http.GET;
import retrofit2.http.Query;
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
    @GET("dish_list.php")
    Observable<Bean> getHttp(@Query("tage_id")String tage_id,@Query("limit")String limit,@Query("page")String page);
}
