package com.movilbox.base;

public abstract class UseCaseSincrono<J,S> {

    public abstract void execute(J request , Callback<S> callback);

    public interface Callback<S>{
        void onResponse(boolean success, S value);
    }
}

