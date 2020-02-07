package com.movilbox.modules.domain.usecase;

import com.movilbox.base.UseCase;
import com.movilbox.modules.data.MainRepository;

public class SetSeen {


    private MainRepository repository;

    public SetSeen(MainRepository repository) {
        this.repository = repository;
    }


    public ResponseValues execute(RequestValues requestValues){
        return new ResponseValues(repository.setSeen(requestValues.getIdPost()));
    }

    public static class RequestValues{
        private int idPost;

        public RequestValues(int idPost) {
            this.idPost = idPost;
        }

        public int getIdPost() {
            return idPost;
        }
    }

    public static class ResponseValues{
        private boolean sucessfull;

        public ResponseValues(boolean sucessfull) {
            this.sucessfull = sucessfull;
        }

        public boolean isSucessfull() {
            return sucessfull;
        }
    }
}
