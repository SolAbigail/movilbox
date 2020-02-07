package com.movilbox.modules.domain.usecase;

import com.movilbox.base.UseCase;
import com.movilbox.modules.data.MainRepository;
import com.movilbox.modules.data.remote.IMainRemoteDataSource;
import com.movilbox.services.Post;

import java.util.List;

public class GetPost extends UseCase<GetPost.RequestValues, GetPost.ResponseValues>{

    private MainRepository repository;

    public GetPost(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getPost(new IMainRemoteDataSource.Callback<List<Post>>() {
            @Override
            public void load(List<Post> item, boolean status) {
                if (status)getUseCaseCallback().onSuccess(new ResponseValues(item));
                else getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{

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
