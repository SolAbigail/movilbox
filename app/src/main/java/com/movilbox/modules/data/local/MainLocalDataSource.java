package com.movilbox.modules.data.local;

import android.util.Log;

import com.movilbox.services.Post;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainLocalDataSource implements IMainLocalDataSource {

    Realm realm;

    public MainLocalDataSource() {
    }

    @Override
    public void savePost(List<Post> postList) {
        realm = Realm.getDefaultInstance();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    int count=0;
                    for (Post post: postList){
                        count++;
                        Number maxid = realm.where(Post.class).max("id");
                        int newkey = (maxid==null)?1:maxid.intValue()+1;
                        Post postDB = realm.createObject(Post.class, newkey);
                        if(count<=20)postDB.setSeen(true);
                        postDB.setTitle(post.getTitle());
                        postDB.setBody(post.getBody());
                        postDB.setUserId(post.getUserId());
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getListPost() {
        realm = Realm.getDefaultInstance();
        List<Post> postList = new ArrayList<>();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Post> posts = realm.where(Post.class).findAll();
                    postList.addAll(posts);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return postList;
    }

    @Override
    public Boolean setSeen(final int idPost) {
        realm = Realm.getDefaultInstance();
        boolean sucessfull = true;
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Post posts = realm.where(Post.class).equalTo("id", idPost).findFirst();
                    posts.setSeen(false);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            sucessfull=false;
        }
        return sucessfull;
    }

    @Override
    public Boolean setFav(int idPost) {
        realm = Realm.getDefaultInstance();
        boolean sucessfull = true;
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Post posts = realm.where(Post.class).equalTo("id", idPost).findFirst();
                    if (posts.isFav())posts.setFav(false);
                    else posts.setFav(true);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            sucessfull=false;
        }
        return sucessfull;
    }

    @Override
    public List<Post> getListPostFav() {
        realm = Realm.getDefaultInstance();
        List<Post> postList = new ArrayList<>();
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Post> posts = realm.where(Post.class).equalTo("fav", true).findAll();
                    postList.addAll(posts);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return postList;
    }
}
