package com.movilbox.base;

public interface BaseFragmentPresenter<T extends BaseView> extends BasePresenter<T> {
    void onAttach();

    void onCreateView();

    void onViewCreated();

    void onActivityCreated();

    void onDestroyView();

    void onDetach();
}
