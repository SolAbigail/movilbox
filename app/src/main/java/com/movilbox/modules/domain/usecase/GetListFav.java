package com.movilbox.modules.domain.usecase;

import com.movilbox.base.UseCase;
import com.movilbox.modules.data.MainRepository;
import com.movilbox.services.Post;

import java.util.List;

public class GetListFav {


    private MainRepository repository;

    public GetListFav(MainRepository repository) {
        this.repository = repository;
    }

    public List<Post> execute(){
        return repository.getListPostFav();
    }


    public static class ResponseValues implements UseCase.ResponseValue{
        private List<Post> postList;

        public ResponseValues(List<Post> postList) {
            this.postList = postList;
        }

        public List<Post> getPostList() {
            return postList;
        }
    }
}
