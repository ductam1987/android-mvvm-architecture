package com.android_mvvm.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android_mvvm.MovieApplication;
import com.android_mvvm.R;
import com.android_mvvm.adapter.MoviesAdapter;
import com.android_mvvm.api.MovieApi;
import com.android_mvvm.databinding.FragmentFavoriteBinding;
import com.android_mvvm.db.modelDB.Favorites;
import com.android_mvvm.model.Results;
import com.android_mvvm.viewmodel.FavoritesFragmentView;
import com.android_mvvm.viewmodel.FavoritesFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

/**
 * Created by Tam Nguyen on 1/15/17.
 */

public class FavoritesFragment extends BaseFragment<FragmentFavoriteBinding,FavoritesFragmentViewModel> implements FavoritesFragmentView{

    private View rootView;

    private RealmResults<Favorites> favorites;
    private MoviesAdapter moviesAdapter;
    private List<Results> results = new ArrayList<>();
    private Realm realmController;

    @Inject
    MovieApi movieApi;

    public FavoritesFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieApplication.getAppComponent().inject(this);
        realmController = Realm.getDefaultInstance();
        viewModel = new FavoritesFragmentViewModel();
        viewModel.attach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bindFragmentView(inflater, R.layout.fragment_favorite,container,false);
        rootView = binding.getRoot();
        binding.setIsLoading(true);
        new loadRealDB().execute();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(moviesAdapter!=null && moviesAdapter.getItemCount() > 0){
            Log.d(Favorites.class.getName(),"Size = " + realmController.where(Favorites.class).findAll().size());
            if(realmController.where(Favorites.class).findAll().size() > moviesAdapter.getItemCount()){
                new loadRealDB().execute();
            }
        }
        Log.d(TAG, "onResume()");
    }

    @Override
    public void error(Throwable e) {

    }

    private class loadRealDB extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            binding.setIsLoading(false);
            Realm realm = Realm.getDefaultInstance();
            favorites = realm.where(Favorites.class).findAll();
            if(results.size()>0){
                results.clear();
            }
            for(Favorites mFavorites : favorites){
                results.add(Results.newMovie(mFavorites.id,mFavorites.title,mFavorites.overview, mFavorites.backdrop,
                        mFavorites.poster,mFavorites.vote,mFavorites.release_date));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(moviesAdapter == null){
                moviesAdapter = new MoviesAdapter();
                moviesAdapter.setResultItems(results);
                binding.listFavorites.setAdapter(moviesAdapter);
            }else{
                moviesAdapter.notifyDataSetChanged();
            }
        }
    }
}
