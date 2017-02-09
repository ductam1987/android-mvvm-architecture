package com.android_mvvm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android_mvvm.MovieApplication;
import com.android_mvvm.R;
import com.android_mvvm.adapter.MoviesAdapter;
import com.android_mvvm.api.MovieApi;
import com.android_mvvm.databinding.FragmentMoviesBinding;
import com.android_mvvm.model.Results;
import com.android_mvvm.util.EndlessRecyclerViewScrollListener;
import com.android_mvvm.viewmodel.MoviesFragmentView;
import com.android_mvvm.viewmodel.MoviesFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Tam Nguyen on 1/15/17.
 */

public class MoviesFragment extends BaseFragment<FragmentMoviesBinding,MoviesFragmentViewModel> implements MoviesFragmentView {

    private static final String TAG = MoviesFragment.class.getName();

    @Inject
    MovieApi movieApi;

    private MoviesAdapter moviesAdapter;
    private View rootView;
    private EndlessRecyclerViewScrollListener scrollListener;

    public MoviesFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieApplication.getAppComponent().inject(this);
        viewModel = new MoviesFragmentViewModel(movieApi);
        viewModel.attach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bindFragmentView(inflater, R.layout.fragment_movies,container,false);
        rootView = binding.getRoot();
        binding.setIsLoading(true);
        viewModel.fetchCharacter(1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.listMovies.setLayoutManager(linearLayoutManager);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                binding.setIsLoading(true);
                viewModel.fetchCharacter(page + 1);
                Log.d(TAG,"page = " + page);
            }
        };
        // Adds the scroll listener to RecyclerView
        binding.listMovies.addOnScrollListener(scrollListener);
        return rootView;
    }

    private List<Results> resultsMaster = new ArrayList<>();
    private int curSize = 0;
    @Override
    public void load(List<Results> results) {
        if(moviesAdapter!= null && moviesAdapter.getItemCount()>0){
            curSize = moviesAdapter.getItemCount();
        }
        binding.setIsLoading(false);
        if(results != null) {
            resultsMaster.addAll(results);
            if(moviesAdapter == null){
                moviesAdapter = new MoviesAdapter();
                moviesAdapter.setResultItems(resultsMaster);
                binding.listMovies.setAdapter(moviesAdapter);
            }else{
                Log.d(TAG,"resultsMaster = " + resultsMaster.size());
                moviesAdapter.notifyItemRangeInserted(curSize, resultsMaster.size() - 1);
            }
        }else{
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.error_title)
                    .content(R.string.error_message)
                    .positiveText(R.string.error_ok)
                    .show();
        }
    }

    @Override
    public void error(Throwable e) {
        Log.d(TAG, e.toString());
        binding.setIsLoading(false);
        new MaterialDialog.Builder(getActivity())
                .title(R.string.error_title)
                .content(R.string.error_message)
                .positiveText(R.string.error_ok)
                .show();
    }
}
