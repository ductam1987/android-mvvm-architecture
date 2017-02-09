package com.android_mvvm.viewmodel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Tam Nguyen on 1/8/17.
 */

public class BaseViewModel<T extends IView> {

    protected CompositeDisposable compositeDisposable;

    T view;

    public BaseViewModel(){
        compositeDisposable = new CompositeDisposable();
    }

    public void attach(T view){
        this.view = view;
    }

    public void detach(){
        view = null;
    }

    public void clearSubscription(){
        compositeDisposable.clear();
    }
}
