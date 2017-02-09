package com.android_mvvm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.URLUtil;

import com.android_mvvm.util.Constant;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Tam Nguyen on 1/9/17.
 */

@AutoValue
public abstract class Results extends BaseObservable implements Serializable {

    public Results(){}

    @SerializedName("id")
    public abstract int id();

    @SerializedName("title")
    public abstract String title();

    @Nullable
    @SerializedName("overview")
    public abstract String overview();

    @Nullable
    @SerializedName("backdrop_path")
    public abstract String backdrop();

    @Nullable
    @SerializedName("poster_path")
    public abstract String poster_path();

    @SerializedName("vote_average")
    public abstract float vote();

    @Nullable
    @SerializedName("release_date")
    public abstract String release_date();

    public static TypeAdapter<Results> typeAdapter(Gson gson){
        return new AutoValue_Results.GsonTypeAdapter(gson);
    }

    @Bindable
    public String getImageUrl() {
        if(poster_path().contains(id() + ".png")){
            return poster_path();
        }else{
            return (Constant.IMAGE_SIZE + poster_path());
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        Log.d(Results.class.getName(),"image URL = " + imageUrl);
        if(URLUtil.isHttpsUrl(imageUrl) || URLUtil.isHttpUrl(imageUrl)){
            view.setImageURI(Uri.parse(imageUrl));
        }else{
            view.setImageURI("file://" + Uri.parse(imageUrl));
        }
    }

    public static Results newMovie(int id, String title, String overview,String backdrop,
                                   String poster,float vote, String releaseDate){
        return new AutoValue_Results(id,title,overview,backdrop,poster,vote,releaseDate);
    }
}
