package com.movilbox.base.activity;

import android.os.Bundle;


public interface BasePresenter<T extends BaseView> extends com.movilbox.base.BasePresenter<T> {
    void setExtras(Bundle extras);
}
