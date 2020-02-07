package com.movilbox.modules.main.listener;

public interface PostListener {

    void seenPost(int idPost, int idUser);

    void favPost(int idPost, boolean state);
}
