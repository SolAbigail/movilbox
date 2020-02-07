package com.movilbox.modules.main.ui;

import com.movilbox.base.activity.BaseView;
import com.movilbox.modules.main.presenter.MainPresenter;
import com.movilbox.services.Post;

import java.util.List;

public interface MainView extends BaseView<MainPresenter> {
    void onErrorRemote();

    void setListPost(List<Post> postList);

    void updateFav(int idPost);
}
