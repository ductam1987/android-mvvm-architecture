package com.android_mvvm.db;

import com.android_mvvm.db.modelDB.Favorites;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Tam Nguyen on 1/13/17.
 */

public class MovieRealmController {

    private static MovieRealmController instance;
    private static Realm realm;

    public static synchronized MovieRealmController getInstance() {

        if (instance == null) {
            instance = new MovieRealmController();
        }
        return instance;
    }

    public MovieRealmController() {
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return realm;
    }

    public void close(){
        realm.close();
    }

    /**
     * @return all objects in the Favorites class
     */
    public RealmResults<Favorites> getFavorites() {
        return realm.where(Favorites.class).findAll();
    }

    //clear all objects from Favorites.class
    public void clearAll() {
        realm.beginTransaction();
        realm.delete(Favorites.class);
        realm.commitTransaction();
    }

    /**
     * @param id
     * @return single object getInstance the given id
     */
    public Favorites getFavorites(int id) {

        return realm.where(Favorites.class).equalTo("id", id).findFirst();
    }
}
