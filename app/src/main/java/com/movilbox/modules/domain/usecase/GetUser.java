package com.movilbox.modules.domain.usecase;

import com.movilbox.base.UseCase;
import com.movilbox.modules.data.MainRepository;
import com.movilbox.modules.data.remote.IMainRemoteDataSource;
import com.movilbox.services.entities.User;


public class GetUser extends UseCase<GetUser.RequestValues, GetUser.ResponseValues>{

    private MainRepository repository;

    public GetUser(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getUser(requestValues.getIdUser(), new IMainRemoteDataSource.Callback<User>() {
            @Override
            public void load(User item, boolean status) {
                if (status)getUseCaseCallback().onSuccess(new ResponseValues(item));
                else getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int idUser;

        public RequestValues(int idUser) {
            this.idUser = idUser;
        }

        public int getIdUser() {
            return idUser;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private User user;

        public ResponseValues(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }
}
