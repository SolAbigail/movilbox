package com.movilbox.modules.domain.usecase;

import com.movilbox.modules.data.MainRepository;

public class SetFav {

    private MainRepository repository;

    public SetFav(MainRepository repository) {
        this.repository = repository;
    }


    public SetFav.ResponseValues execute(SetFav.RequestValues requestValues){
        return new SetFav.ResponseValues(repository.setFav(requestValues.getIdPost()));
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
