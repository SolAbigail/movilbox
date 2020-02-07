package com.movilbox.modules.data.remote;

import android.util.Log;

import com.movilbox.api.service.ApiUtils;
import com.movilbox.api.service.Services;
import com.movilbox.services.Post;
import com.movilbox.services.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainRemoteDataSource implements IMainRemoteDataSource {

    private Services services;

    public MainRemoteDataSource() {
    }

    @Override
    public void getPost(final Callback<List<Post>> callback) {
        services = ApiUtils.getService();
        Call<List<Post>> call = services.getPost();
        call.enqueue(new retrofit2.Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.body().isEmpty())
                {
                    List<Post> postList =response.body();
                    callback.load(postList, true);
                }
                else callback.load(null, false);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                callback.load(null, false);
            }
        });

    }

    @Override
    public void getUser(int idUser, Callback<User> callback) {
        services = ApiUtils.getService();
        Call<User> call = services.getUSer(idUser);
        call.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                {
                    User user =response.body();
                    callback.load(user, true);
                }
                else callback.load(null, false);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                callback.load(null, false);
            }
        });
    }
}
