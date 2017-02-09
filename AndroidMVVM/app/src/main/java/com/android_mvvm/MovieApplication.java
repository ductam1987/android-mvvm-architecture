package com.android_mvvm;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.android_mvvm.di.component.AppComponent;
import com.android_mvvm.di.component.DaggerAppComponent;
import com.android_mvvm.di.module.AppModule;
import com.android_mvvm.di.module.MovieModule;
import com.android_mvvm.di.module.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Tam Nguyen on 1/15/17.
 */

public class MovieApplication extends Application{

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        appComponent = DaggerAppComponent.builder()
                .movieModule(new MovieModule())
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();

    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    public static MovieApplication get(Context context) {
        return (MovieApplication) context.getApplicationContext();
    }
}
