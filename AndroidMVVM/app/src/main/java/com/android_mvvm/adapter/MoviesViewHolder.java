package com.android_mvvm.adapter;

import android.support.v7.widget.RecyclerView;

import com.android_mvvm.databinding.MovieItemBinding;
import com.android_mvvm.model.Results;

/**
 * Created by Tam Nguyen on 1/10/17.
 */

public class MoviesViewHolder extends RecyclerView.ViewHolder{

    MovieItemBinding binding;
    public MoviesViewHolder(MovieItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(Results trendsItem) {
        binding.setResult(trendsItem);
        binding.executePendingBindings();
    }
}
