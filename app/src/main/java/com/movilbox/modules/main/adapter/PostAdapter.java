package com.movilbox.modules.main.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movilbox.R;
import com.movilbox.modules.main.listener.PostListener;
import com.movilbox.services.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter {

    private List<Post> postList;
    private PostListener listener;

    public PostAdapter(List<Post> postList, PostListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderPost(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post post = postList.get(position);
        HolderPost holderPost = (HolderPost) holder;
        holderPost.bind(post, listener);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setPostList(List<Post> postList){
        this.postList.clear();
        this.postList.addAll(postList);
        notifyDataSetChanged();
    }

    public void updateItemFav(int idPost) {
        int positionn = idPost-1;
        Post post = postList.get(positionn);
        if (post!=null){
            postList.set(positionn, post);
            notifyItemChanged(positionn);
        }
    }
}
