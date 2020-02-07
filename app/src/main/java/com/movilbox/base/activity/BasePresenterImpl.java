package com.movilbox.base.activity;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.movilbox.base.UseCaseHandler;


public abstract class BasePresenterImpl<V extends com.movilbox.base.activity.BaseView> implements BasePresenter<V> {

    protected abstract String getTag();

    protected V view;
    protected UseCaseHandler handler;
    protected Resources res;

    public BasePresenterImpl(UseCaseHandler handler, Resources res) {
        this.handler = handler;
        this.res = res;
    }

    public Bundle getExtras() {
        return extras;
    }

    @Override
    public void attachView(V view) {
        Log.d(getTag(), "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(getTag(), "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(getTag(), "onStart");
    }

    @Override
    public void onResume() {
        Log.d(getTag(), "onResume");
    }

    @Override
    public void onPause() {
        Log.d(getTag(), "onPause");
    }

    @Override
    public void onStop() {
        Log.d(getTag(), "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(getTag(), "onDestroy");
        this.view =null;
    }

    protected Bundle extras;

    @Override
    public void setExtras(Bundle extras) {
        Log.d(getTag(), "setExtras");
        this.extras = extras;
    }


    @Override
    public void onBackPressed() {
        Log.d(getTag(), "onBackPressed");
    }


}
