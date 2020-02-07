package com.movilbox.modules.main.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.movilbox.R;
import com.movilbox.modules.main.listener.PostListener;
import com.movilbox.services.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HolderPost extends RecyclerView.ViewHolder {


    @BindView(R.id.fav)
    ImageView fav;
    @BindView(R.id.body)
    TextView body;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.cardview)
    CardView cardView;

    private Post post;
    private PostListener postListener;

    public HolderPost(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Post post, PostListener postListener){
        this.post = post;
        this.postListener = postListener;
        body.setText(post.getBody());
        title.setText(post.getTitle());


        if (post.isFav()){
            Drawable drawable_y = itemView.getContext().getResources().getDrawable(R.drawable.ic_star_yellow);
            fav.setBackground(drawable_y);

        }else {
            Drawable drawable_c = itemView.getContext().getResources().getDrawable(R.drawable.ic_star);
            fav.setBackground(drawable_c);
        }
        if (post.isSeen())
            cardView.setCardBackgroundColor(Color.parseColor("#f5f5f5"));
        else cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
    }

    @OnClick({R.id.fav, R.id.cardview})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.fav:
                postListener.favPost(post.getId(), post.isFav());
                break;
            case R.id.cardview:
                postListener.seenPost(post.getId(), post.getUserId());
                break;
        }
    }
}
