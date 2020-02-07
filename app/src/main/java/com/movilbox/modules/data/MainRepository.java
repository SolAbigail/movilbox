package com.movilbox.modules.data;

import com.movilbox.modules.data.local.IMainLocalDataSource;
import com.movilbox.modules.data.local.MainLocalDataSource;
import com.movilbox.modules.data.remote.IMainRemoteDataSource;
import com.movilbox.modules.data.remote.MainRemoteDataSource;
import com.movilbox.services.Post;
import com.movilbox.services.entities.User;

import java.util.List;

public class MainRepository implements IMainLocalDataSource, IMainRemoteDataSource {

    public MainLocalDataSource localDataSource;
    public MainRemoteDataSource remoteDataSource;

    public MainRepository(MainLocalDataSource localDataSource, MainRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getPost(Callback<List<Post>> callback) {
        remoteDataSource.getPost(callback);
    }

    @Override
    public void getUser(int idUser, Callback<User> callback) {
        remoteDataSource.getUser(idUser, callback);
    }

    @Override
    public void savePost(List<Post> postList) {
        localDataSource.savePost(postList);
    }

    @Override
    public List<Post> getListPost() {
        return localDataSource.getListPost();
    }

    @Override
    public Boolean setSeen(int idPost) {
        return localDataSource.setSeen(idPost);
    }

    @Override
    public Boolean setFav(int idPost) {
        return localDataSource.setFav(idPost);
    }

    @Override
    public List<Post> getListPostFav() {
        return localDataSource.getListPostFav();
    }
}
