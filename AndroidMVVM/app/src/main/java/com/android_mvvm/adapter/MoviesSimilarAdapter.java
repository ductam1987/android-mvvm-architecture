package com.android_mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android_mvvm.R;
import com.android_mvvm.databinding.MovieItemBinding;
import com.android_mvvm.model.Results;

import java.util.List;

/**
 * Created by Tam Nguyen on 1/17/17.
 */

public class MoviesSimilarAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private static final String TAG = MoviesSimilarAdapter.class.getName();

    private List<Results> itemList;
    private Results dataResults;
    private MovieItemBinding binding;

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false);
        return new MoviesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        dataResults = itemList.get(position);
        holder.update(dataResults);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setResultItems(List<Results> resultItems) {
        this.itemList = resultItems;
        notifyDataSetChanged();
    }
}
