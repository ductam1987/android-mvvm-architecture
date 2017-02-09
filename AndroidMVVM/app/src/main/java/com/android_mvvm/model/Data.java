package com.android_mvvm.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tam Nguyen on 1/8/17.
 */

@AutoValue
public abstract class Data {

    @SerializedName("page")
    public abstract int page();

    @SerializedName("results")
    public abstract List<Results> resultsList();

    public static TypeAdapter<Data> typeAdapter(Gson gson){
        return new AutoValue_Data.GsonTypeAdapter(gson);
    }

}
