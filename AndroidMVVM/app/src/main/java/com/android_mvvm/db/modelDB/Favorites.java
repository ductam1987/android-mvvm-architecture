package com.android_mvvm.db.modelDB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Tam Nguyen on 1/16/17.
 */

public class Favorites extends RealmObject {

    @PrimaryKey
    public int id;

    public String title;

    public String overview;

    public String backdrop;

    public String poster;

    public float vote;

    public String release_date;
}
