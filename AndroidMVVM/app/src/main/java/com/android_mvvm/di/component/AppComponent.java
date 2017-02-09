package com.android_mvvm.di.component;

import android.content.SharedPreferences;

import com.android_mvvm.di.module.AppModule;
import com.android_mvvm.di.module.MovieModule;
import com.android_mvvm.di.module.NetworkModule;
import com.android_mvvm.view.FavoritesFragment;
import com.android_mvvm.view.MovieDetailActivity;
import com.android_mvvm.view.MoviesFragment;
import com.android_mvvm.viewmodel.FavoritesFragmentViewModel;
import com.android_mvvm.viewmodel.MovieDetailActivityViewModel;
import com.android_mvvm.viewmodel.MoviesFragmentViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Tam Nguyen on 1/8/17.
 */

@Singleton
@Component(modules ={AppModule.class, NetworkModule.class, MovieModule.class} )
public interface AppComponent {

    void inject(MoviesFragment activity);
    void inject(MoviesFragmentViewModel activity);

    void inject(FavoritesFragment activity);
    void inject(FavoritesFragmentViewModel activity);

    void inject(MovieDetailActivity activity);
    void inject(MovieDetailActivityViewModel activity);
}
