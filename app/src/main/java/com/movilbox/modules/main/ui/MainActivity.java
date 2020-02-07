package com.movilbox.modules.main.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.movilbox.R;
import com.movilbox.base.UseCaseHandler;
import com.movilbox.base.UseCaseThreadPoolScheduler;
import com.movilbox.base.activity.BaseActivity;
import com.movilbox.base.activity.View;
import com.movilbox.base.fragment.BaseFragmentListener;
import com.movilbox.modules.data.MainRepository;
import com.movilbox.modules.data.local.MainLocalDataSource;
import com.movilbox.modules.data.remote.MainRemoteDataSource;
import com.movilbox.modules.domain.usecase.GetListFav;
import com.movilbox.modules.domain.usecase.GetPost;
import com.movilbox.modules.domain.usecase.GetPostList;
import com.movilbox.modules.domain.usecase.GetUser;
import com.movilbox.modules.domain.usecase.SavePost;
import com.movilbox.modules.domain.usecase.SetFav;
import com.movilbox.modules.domain.usecase.SetSeen;
import com.movilbox.modules.main.adapter.PostAdapter;
import com.movilbox.modules.main.listener.PostListener;
import com.movilbox.modules.main.presenter.MainPresenter;
import com.movilbox.modules.main.presenter.MainPresenterImpl;
import com.movilbox.modules.user.ui.UserDialogFragment;
import com.movilbox.services.Post;;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, PostListener, android.view.View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, BaseFragmentListener {

    @BindView(R.id.rvPost)
    RecyclerView rvPost;
    @BindView(R.id.ic_star)
    ImageView ic_start;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    PostAdapter adapter;
    private boolean favFilter=false;

    @Override
    protected String getTag() {
        return MainActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected MainPresenter getPresenter() {
        MainRepository repository = new MainRepository(new MainLocalDataSource(), new MainRemoteDataSource());
        presenter = new MainPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPost(repository),
                new SavePost(repository),
                new GetPostList(repository),
                new SetSeen(repository),
                new SetFav(repository),
                new GetListFav(repository),
                new GetUser(repository));
        return presenter;
    }

    @Override
    protected MainView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ic_start.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        setadapter();
    }

    private void setadapter() {
        adapter = new PostAdapter(new ArrayList<>(), this);
        rvPost.setLayoutManager(new LinearLayoutManager(this));
        rvPost.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (favFilter) presenter.listFav();
        else presenter.setGetPostListList();
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
    public void onErrorRemote() {

    }

    @Override
    public void setListPost(List<Post> postList) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setPostList(postList);
    }

    @Override
    public void updateFav(int idPost) {
        adapter.updateItemFav(idPost);
    }

    @Override
    public void seenPost(int idPost, int idUser) {
        presenter.onSetSeen(idPost);
        onInitiDialog(idUser);
        adapter.updateItemFav(idPost);
    }

    private void onInitiDialog(int idUser) {
        FragmentManager manager = getSupportFragmentManager();
        UserDialogFragment dialogFragment= UserDialogFragment.newInstance(idUser);
        dialogFragment.show(manager, UserDialogFragment.class.getSimpleName());
    }

    @Override
    public void favPost(int idPost, boolean state) {
        presenter.setFav(idPost);
    }


    @Override
    public void onClick(android.view.View view) {
        switch (R.id.ic_star){
            case R.id.ic_star:
                setFilter();
                break;
        }
    }

    private void setFilter() {
        if (favFilter){
            this.favFilter=false;
            presenter.setGetPostListList();
            Drawable drawable_y = getApplicationContext().getResources().getDrawable(R.drawable.ic_star);
            ic_start.setBackground(drawable_y);
        }else {
            this.favFilter=true;
            presenter.listFav();
            Drawable drawable_c = getApplicationContext().getResources().getDrawable(R.drawable.ic_star_yellow);
            ic_start.setBackground(drawable_c);
        }
    }

    @Override
    public void onRefresh() {
        if (favFilter) presenter.listFav();
        else presenter.setGetPostListList();

    }
}
