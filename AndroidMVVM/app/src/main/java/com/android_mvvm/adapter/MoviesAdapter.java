package com.android_mvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android_mvvm.R;
import com.android_mvvm.databinding.MovieItemBinding;
import com.android_mvvm.model.Results;
import com.android_mvvm.util.Constant;
import com.android_mvvm.view.MovieDetailActivity;

import java.util.List;

/**
 * Created by Tam Nguyen on 1/10/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private static final String TAG = MoviesAdapter.class.getName();

    private List<Results> itemList;
    private Results dataResults;
    private MovieItemBinding binding;

    public MoviesAdapter() {
    }

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

        holder.itemView.setOnClickListener(view -> {
            Intent mIntent = new Intent(view.getContext(), MovieDetailActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable(Constant.ID_CHARACTER, itemList.get(position));
            mIntent.putExtras(mBundle);
            view.getContext().startActivity(mIntent);
        });
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
