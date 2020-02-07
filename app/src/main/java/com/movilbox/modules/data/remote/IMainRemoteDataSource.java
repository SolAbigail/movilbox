package com.movilbox.modules.data.remote;

import com.movilbox.services.Post;
import com.movilbox.services.entities.User;

import java.util.List;

public interface IMainRemoteDataSource {

    interface Callback<T>{
        void load(T item, boolean status);
    }

    void getPost(Callback<List<Post>> callback);

    void getUser(int idUser, Callback<User> callback);
}
