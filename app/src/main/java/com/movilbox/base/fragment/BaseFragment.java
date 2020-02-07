package com.movilbox.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.movilbox.base.activity.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView<P>, P extends BaseFragmentPresenter<V>, L> extends Fragment implements BaseView<P>{


    protected abstract String getLogTag();

    protected abstract P getPresenter();

    protected abstract V getBaseView();

    //protected abstract Bundle getExtrasInf();

    protected P presenter;

    protected abstract View inflateView(LayoutInflater inflater, ViewGroup container);

    protected abstract ViewGroup getRootLayout();

    protected abstract ProgressBar getProgressBar();


    public BaseFragment() {
    }

    /*public static <T extends AppCompatActivity> Intent getStartIntent(Context context, Class<T> tClass) {
        return new Intent(context, tClass);
    }*/

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
        if (presenter != null) presenter.setExtras(getArguments());
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
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        if (presenter != null) presenter.onCreateView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        setupConteView();

        //setRetainInstance(true);
        if (presenter != null) presenter.onViewCreated();


    }
    private void setupConteView() {
        if(getRootLayout()!=null)getRootLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getTag()," setupConteView hideTeclado");
                hideTeclado();
            }
        });
    }


    @Override
    public void onStart() {
        Log.d(getLogTag(), "onResume");
        super.onStart();
        if (presenter != null) presenter.onStart();
    }

    @Override
    public void onResume() {
        Log.d(getLogTag(), "onResume");
        super.onResume();
        if (presenter != null) presenter.onResume();
    }

    @Override
    public void onPause() {
        Log.d(getLogTag(), "onPause");
        if(getRootLayout()!=null)hideTeclado();
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


    protected Integer[] getEditTextList(){
        return null;
    }




    public void hideTeclado(){
        ViewGroup view = getRootLayout();
        if(view==null)return;
        if(getEditTextList()==null)return;
        for (Integer editText : getEditTextList()){
            hideSoftKeyboard(view.findViewById(editText));
        }
    }
    /**
     * Hide keyboard while focus is moved
     */
    private void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        0);
                view.clearFocus();
            }
        }
    }



}
