package com.movilbox.modules.main.presenter;

import com.movilbox.base.activity.BasePresenter;
import com.movilbox.modules.main.ui.MainView;

public interface MainPresenter extends BasePresenter<MainView> {
    void setFav(int idPost);

    void listFav();

    void setGetPostListList();

    void onSetSeen(int idPost);
}
