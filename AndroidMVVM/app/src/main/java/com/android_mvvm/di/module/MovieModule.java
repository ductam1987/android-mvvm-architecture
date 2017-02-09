package com.android_mvvm.di.module;


import com.android_mvvm.api.MovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Tam Nguyen on 1/9/17.
 */


@Module
public class MovieModule {

    @Provides
    @Singleton
    MovieApi provideRetrofit(Retrofit retrofit){
        return retrofit.create(MovieApi.class);
    }

}
