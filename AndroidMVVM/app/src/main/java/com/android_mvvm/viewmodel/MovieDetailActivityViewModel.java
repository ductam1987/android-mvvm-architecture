package com.android_mvvm.viewmodel;

import com.android_mvvm.api.MovieApi;
import com.android_mvvm.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tam Nguyen on 1/17/17.
 */

public class MovieDetailActivityViewModel extends BaseViewModel<MovieDetailActivityView> {

    private MovieApi movieApi;

    public MovieDetailActivityViewModel(MovieApi movieApi){
        this.movieApi = movieApi;
    }

    public void fetchMovieSimilar(int id,int page){
        compositeDisposable.add(movieApi.getSimilarMovie(id,Constant.Movie_ApiKey,page)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> view.load(data.resultsList()),
                        throwable -> view.error(throwable)));
    }
}
