package com.movilbox.modules.main.presenter;

import android.content.res.Resources;
import android.util.Log;

import com.movilbox.base.UseCase;
import com.movilbox.base.UseCaseHandler;
import com.movilbox.base.activity.BasePresenterImpl;
import com.movilbox.modules.domain.usecase.GetListFav;
import com.movilbox.modules.domain.usecase.GetPost;
import com.movilbox.modules.domain.usecase.GetPostList;
import com.movilbox.modules.domain.usecase.GetUser;
import com.movilbox.modules.domain.usecase.SavePost;
import com.movilbox.modules.domain.usecase.SetFav;
import com.movilbox.modules.domain.usecase.SetSeen;
import com.movilbox.modules.main.ui.MainView;
import com.movilbox.services.Post;

import java.util.List;

import butterknife.OnClick;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {

    private GetPost getPost;
    private SavePost savePost;
    private GetPostList getPostList;
    private SetSeen setSeen;
    private GetListFav getListFav;
    private SetFav setFav;
    private GetUser getUser;

    public MainPresenterImpl(UseCaseHandler handler, Resources res, GetPost getPost, SavePost savePost, GetPostList getPostList, SetSeen setSeen, SetFav setFav, GetListFav getListFav, GetUser getUser) {
        super(handler, res);
        this.getPost = getPost;
        this.savePost = savePost;
        this.getPostList = getPostList;
        this.setSeen = setSeen;
        this.setFav = setFav;
        this.getListFav = getListFav;
        this.getUser = getUser;
    }

    @Override
    protected String getTag() {
        return MainPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        setGetPost();
    }

    public  void setGetPost(){
        handler.execute(getPost, new GetPost.RequestValues(), new UseCase.UseCaseCallback<GetPost.ResponseValues>() {
            @Override
            public void onSuccess(GetPost.ResponseValues response) {
               savePost.execute(new SavePost.ResquestValues(response.getPostList()));
                setGetPostListList();
            }

            @Override
            public void onError() {
                if(view!=null)view.onErrorRemote();
            }
        });
    }

    @Override
    public void setGetPostListList(){
        List<Post> postList = getPostList.execute();
        if (view!=null)view.setListPost(postList);
    }

    @Override
    public void onSetSeen(int idPost) {
        SetSeen.ResponseValues succesfu = setSeen.execute(new SetSeen.RequestValues(idPost));
        if (succesfu.isSucessfull())Log.d("SetSeen", "true");
    }

    @Override
    public void setFav(int idPost) {
        SetFav.ResponseValues respuest = setFav.execute(new SetFav.RequestValues(idPost));
        if (respuest.isSucessfull()){
            if (view!=null)view.updateFav(idPost);
        }
    }

    @Override
    public void listFav() {
        List<Post> postList = getListFav.execute();
        if (view!=null)view.setListPost(postList);
    }
}
