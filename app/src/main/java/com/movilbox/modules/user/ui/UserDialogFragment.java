package com.movilbox.modules.user.ui;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.movilbox.R;
import com.movilbox.base.UseCaseHandler;
import com.movilbox.base.UseCaseThreadPoolScheduler;
import com.movilbox.base.dialogFragment.BaseDialogFragment;
import com.movilbox.modules.data.MainRepository;
import com.movilbox.modules.data.local.MainLocalDataSource;
import com.movilbox.modules.data.remote.MainRemoteDataSource;
import com.movilbox.modules.domain.usecase.GetUser;
import com.movilbox.modules.main.listener.UserListener;
import com.movilbox.modules.user.presenter.UserPresenter;
import com.movilbox.modules.user.presenter.UserPresenterImpl;
import com.movilbox.services.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDialogFragment extends BaseDialogFragment<UserView, UserPresenter, UserListener> implements UserView, UserListener {


    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.nombreUser)
    TextView nombreUser;
    @BindView(R.id.zipcode)
    TextView zipcode;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.suite)
    TextView suite;
    @BindView(R.id.street)
    TextView street;
    @BindView(R.id.website)
    TextView website;
    @BindView(R.id.phone)
    TextView phone;

    public static UserDialogFragment newInstance(int idUser){
        UserDialogFragment fragment = new UserDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idUser", idUser);
        fragment.setArguments(bundle);
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return UserDialogFragment.class.getCanonicalName();
    }

    @Override
    protected UserPresenter getPresenter() {
        MainRepository repository = new MainRepository(new MainLocalDataSource(), new MainRemoteDataSource());
        presenter = new UserPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetUser(repository));
        return presenter;
    }

    @Override
    protected UserView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.dialog_user, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void setUser(User user) {
        email.setText(user.getEmail());
        userName.setText(user.getUsername());
        nombreUser.setText(user.getName());
        street.setText("Street: "+user.getAddress().getStreet());
        suite.setText("Suite: "+user.getAddress().getSuite());
        city.setText("City: "+user.getAddress().getCity());
        zipcode.setText("Zipcode: "+user.getAddress().getZipcode());
        phone.setText("Phone: "+user.getPhone());
        website.setText(user.getWebsite());
    }
}
