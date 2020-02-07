package com.movilbox.modules.data.local;

import com.movilbox.services.Post;

import java.util.List;

public interface IMainLocalDataSource {

    void savePost(List<Post> postList);

    List<Post> getListPost();

    Boolean setSeen(int idPost);

    Boolean setFav(int idPost);

    List<Post> getListPostFav();
}
