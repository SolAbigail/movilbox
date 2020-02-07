package com.movilbox.modules.user.ui;

import com.movilbox.base.activity.BaseView;
import com.movilbox.modules.user.presenter.UserPresenter;
import com.movilbox.services.entities.User;

public interface UserView extends BaseView<UserPresenter> {
    void setUser(User user);
}
