package com.movilbox.api.service;

import com.movilbox.services.Post;
import com.movilbox.services.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Services {

    //getPost
    @GET("posts")
    Call<List<Post>> getPost();
    //getUSerId
    @GET("users/{id}")
    Call<User> getUSer(@Path("id") int id);


}
