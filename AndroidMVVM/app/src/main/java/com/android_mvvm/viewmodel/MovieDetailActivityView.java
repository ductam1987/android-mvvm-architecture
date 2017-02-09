package com.android_mvvm.viewmodel;

import com.android_mvvm.model.Results;

import java.util.List;

/**
 * Created by Tam Nguyen on 1/17/17.
 */

public interface MovieDetailActivityView extends IView {

    void load(List<Results> results);
}
