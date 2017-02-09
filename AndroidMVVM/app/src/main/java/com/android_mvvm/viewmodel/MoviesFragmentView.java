package com.android_mvvm.viewmodel;

import com.android_mvvm.model.Results;

import java.util.List;

/**
 * Created by Tam Nguyen on 1/15/17.
 */

public interface MoviesFragmentView extends IView {

    void load(List<Results> results);
}
