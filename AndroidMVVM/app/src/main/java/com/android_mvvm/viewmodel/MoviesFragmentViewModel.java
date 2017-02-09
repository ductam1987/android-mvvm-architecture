package com.android_mvvm.viewmodel;

import com.android_mvvm.api.MovieApi;
import com.android_mvvm.util.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tam Nguyen on 1/15/17.
 */

public class MoviesFragmentViewModel extends BaseViewModel<MoviesFragmentView> {

    private MovieApi movieApi;

    public MoviesFragmentViewModel(MovieApi movieApi){
        this.movieApi = movieApi;
    }

    public void fetchCharacter(int page){
        compositeDisposable.add(movieApi.getListMovie(Constant.Movie_ApiKey,page)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> view.load(data.resultsList()),
                        throwable -> view.error(throwable)));
    }
}
