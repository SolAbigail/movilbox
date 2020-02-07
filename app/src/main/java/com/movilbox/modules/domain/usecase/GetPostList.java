package com.movilbox.modules.domain.usecase;

import com.movilbox.modules.data.MainRepository;
import com.movilbox.services.Post;

import java.util.List;

public class GetPostList {

    private MainRepository repository;

    public GetPostList(MainRepository repository) {
        this.repository = repository;
    }

    public List<Post> execute(){
        return repository.getListPost();
    }

    public static class ResponseValues{
        private List<Post> postList;

        public ResponseValues(List<Post> postList) {
            this.postList = postList;
        }

        public List<Post> getPostList() {
            return postList;
        }
    }
}
