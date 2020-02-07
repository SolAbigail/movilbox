package com.movilbox.modules.domain.usecase;

import com.movilbox.base.UseCase;
import com.movilbox.modules.data.MainRepository;
import com.movilbox.services.Post;

import java.util.List;

public class SavePost {

    private MainRepository repository;

    public SavePost(MainRepository repository) {
        this.repository = repository;
    }

    public void execute(ResquestValues resquestValues){
        repository.savePost(resquestValues.postList);
    }

    public static class ResquestValues{
        private List<Post> postList;

        public ResquestValues(List<Post> postList) {
            this.postList = postList;
        }

        public List<Post> getPostList() {
            return postList;
        }
    }
}
