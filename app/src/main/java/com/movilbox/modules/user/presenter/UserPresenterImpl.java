package com.movilbox.modules.user.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.movilbox.base.UseCase;
import com.movilbox.base.UseCaseHandler;
import com.movilbox.base.fragment.BaseFragmentPresenterImpl;
import com.movilbox.modules.domain.usecase.GetUser;
import com.movilbox.modules.user.ui.UserView;

public class UserPresenterImpl extends BaseFragmentPresenterImpl<UserView> implements UserPresenter {


    private GetUser getUser;
    private int idUser;

    public UserPresenterImpl(UseCaseHandler handler, Resources res, GetUser getUser) {
        super(handler, res);
        this.getUser = getUser;
    }

    @Override
    protected String getTag() {
        return UserPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onResume() {
        super.onResume();
        setGetUser();
    }

    public void setGetUser(){
        handler.execute(getUser, new GetUser.RequestValues(idUser), new UseCase.UseCaseCallback<GetUser.ResponseValues>() {
            @Override
            public void onSuccess(GetUser.ResponseValues response) {
                if(view!=null)view.setUser(response.getUser());
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        idUser = extras.getInt("idUser", 0);
    }
}
