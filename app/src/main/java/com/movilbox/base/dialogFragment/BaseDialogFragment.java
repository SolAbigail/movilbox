package com.movilbox.base.dialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.movilbox.base.activity.BaseView;
import com.movilbox.base.fragment.BaseFragmentListener;
import com.movilbox.base.fragment.BaseFragmentPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseDialogFragment<V extends BaseView<P>, P extends BaseFragmentPresenter<V>, L> extends DialogFragment implements BaseView<P> {


    protected abstract String getLogTag();

    protected abstract P getPresenter();

    protected abstract V getBaseView();

    //protected abstract Bundle getExtrasInf();

    protected P presenter;

    protected abstract View inflateView(LayoutInflater inflater, ViewGroup container);

    protected abstract ViewGroup getRootLayout();

    protected abstract ProgressBar getProgressBar();

    public BaseDialogFragment() {
    }


    private ProgressBar progressBar;
    protected L listener;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        Log.d(getLogTag(), "onAttach");
        super.onAttach(context);
        listener = (L) getTargetFragment();
        if (listener != null) return;
        if (context instanceof BaseFragmentListener) {
            listener = (L) context;
        } else {
            throw new ClassCastException(
                    context.toString() + "must implement BaseFragmentListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreate");
        super.onCreate(savedInstanceState);
        setupPresenter();
        if (presenter != null) presenter.onCreate();
        if (getArguments() != null) {
        }
    }
    private void setupPresenter() {
        if (presenter == null) {
            presenter = getPresenter();
        }
        setPresenter(presenter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreateView");
        if (presenter != null) presenter.onCreateView();
        View view =  inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        //setRetainInstance(true);
        if (presenter != null) presenter.setExtras(getArguments());
        if (presenter != null) presenter.onViewCreated();
    }


    @Override
    public void onStart() {
        Log.d(getLogTag(), "onResume");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
        if (presenter != null) presenter.onStart();
    }

    @Override
    public void onResume() {
        Log.d(getLogTag(), "onResume");
        super.onResume();
        try {
            if (presenter != null) presenter.onResume();
        }catch (Exception e){}
    }

    @Override
    public void onPause() {
        Log.d(getLogTag(), "onPause");
        super.onPause();
        if (presenter != null) presenter.onPause();
    }

    @Override
    public void onStop() {
        Log.d(getLogTag(), "onStop");
        super.onStop();
        if (presenter != null) presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(getLogTag(), "onDestroyView");
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(getLogTag(), "onDestroy");
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.d(getLogTag(), "onDetach");
        listener = null;
        super.onDetach();
        if (presenter != null) presenter.onDetach();
    }


    @Override
    public void setPresenter(P presenter) {
        if (presenter != null) {
            presenter.attachView(getBaseView());
        }
    }



}