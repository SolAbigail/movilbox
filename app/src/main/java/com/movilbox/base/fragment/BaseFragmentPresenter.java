package com.movilbox.base.fragment;


import com.movilbox.base.activity.BasePresenter;
import com.movilbox.base.activity.BaseView;


public interface BaseFragmentPresenter<T extends BaseView> extends BasePresenter<T> {
    void onAttach();
    void onCreateView();
    void onViewCreated();
    void onActivityCreated();
    void onDestroyView();
    void onDetach();
}
