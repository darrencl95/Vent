package com.pingus.vent.Model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.IconType;
import com.like.LikeButton;
import com.pingus.vent.R;

import java.util.ArrayList;

/**
 * Array Adapter for Comments objects
 * Created by smant on 7/23/2017.
 */

public class CommentsArrayAdapter extends RecyclerView.Adapter<CommentsArrayAdapter.ThisViewHolder> {
    private ArrayList<Comment> commentList;

    public class ThisViewHolder extends RecyclerView.ViewHolder {
        public TextView username, timestamp, body, numlikes, bold;
        public ImageView profilePic;
        public LikeButton likeButton;

        public ThisViewHolder(View view) {
            super(view);
            profilePic = (ImageView) view.findViewById(R.id.comment_prof_pic);
            username = (TextView) view.findViewById(R.id.comment_user_name);
            timestamp = (TextView) view.findViewById(R.id.comment_time_stamp);
            body = (TextView) view.findViewById(R.id.comment_body);
            numlikes = (TextView) view.findViewById(R.id.comment_num_likes);
            likeButton = (LikeButton) view.findViewById(R.id.like_button);
            bold = (TextView) view.findViewById(R.id.bold);
        }
    }

    public CommentsArrayAdapter(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public CommentsArrayAdapter.ThisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_post_row, parent, false);

        return new CommentsArrayAdapter.ThisViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(CommentsArrayAdapter.ThisViewHolder holder, int position) {
        final Comment currentPost = commentList.get(position);
        holder.profilePic.setImageResource(currentPost.getProfilePic());

        holder.username.setText(currentPost.getUsername());

        holder.timestamp.setText(currentPost.getTimestamp());

        holder.body.setText(currentPost.getBody());

        final LikeButton liker = holder.likeButton;
        final TextView numLikes = holder.numlikes;
        liker.setIcon(IconType.Heart);
        numLikes.setText(String.valueOf(currentPost.getLikes()));

        liker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(liker.isLiked()) {
                    liker.setLiked(false);
                    numLikes.setText(String.valueOf(currentPost.getLikes()));
                    //TODO Access database to change value
                } else {
                    liker.setLiked(true);
                    numLikes.setText(String.valueOf(currentPost.getLikes() + 1));
                    //TODO Access database to change value
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

}
