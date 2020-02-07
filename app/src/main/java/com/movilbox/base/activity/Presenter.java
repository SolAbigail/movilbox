package com.movilbox.base.activity;

import android.content.res.Resources;

import com.movilbox.R;
import com.movilbox.base.UseCaseHandler;


public class Presenter extends BasePresenterImpl<View> {

    public Presenter(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    public void attachView(View view) {
        super.attachView(view);
    }

    @Override
    protected String getTag() {
        return Presenter.class.getSimpleName();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (view != null) {
            view.changeBackground(R.color.colorPrimary);
        }

    }


}
