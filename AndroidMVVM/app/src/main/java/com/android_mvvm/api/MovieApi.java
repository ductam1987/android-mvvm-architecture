package com.android_mvvm.api;

import com.android_mvvm.model.Data;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tam Nguyen on 1/9/17.
 */

public interface MovieApi {

    @GET("movie/now_playing")
    Flowable<Data> getListMovie(@Query("api_key") String api,
                                @Query("page") int page);


    @GET("movie/{id}/similar")
    Flowable<Data> getSimilarMovie(@Path("id") int id,
                                   @Query("api_key") String api,
                                   @Query("page") int page);


}

