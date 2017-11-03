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
 * ArrayAdapter for Posts Objects, signals proper placement on layout, etc.
 * Created by smant on 6/27/2017.
 */

public class PostsArrayAdapter  extends RecyclerView.Adapter<PostsArrayAdapter.MyViewHolder> {
    private ArrayList<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, timestamp, postComment, numLikes, bold;
        public ImageView profilePic;
        public Button commentButton;
        public LikeButton likeButton;

        public MyViewHolder(View view) {
            super(view);
            profilePic = (ImageView) view.findViewById(R.id.prof_pic);
            username = (TextView) view.findViewById(R.id.user_name);
            timestamp = (TextView) view.findViewById(R.id.time_stamp);
            postComment = (TextView) view.findViewById(R.id.post_button);
            numLikes = (TextView) view.findViewById(R.id.num_likes);
            likeButton = (LikeButton) view.findViewById(R.id.like_button);
            commentButton = (Button) view.findViewById(R.id.comment_button);
            bold = (TextView) view.findViewById(R.id.bold);
        }
    }

    public PostsArrayAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wall_post_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Post currentPost = postList.get(position);
        holder.profilePic.setImageResource(currentPost.getProf_pic());

        holder.username.setText(currentPost.getUsername());

        holder.timestamp.setText(currentPost.getTimestamp());

        holder.postComment.setText(currentPost.getComment());

        final LikeButton liker = holder.likeButton;
        final Button commenter = holder.commentButton;
        final TextView numLikes = holder.numLikes;
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

        commenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Goto Comments Activity, allow user to see/post comments
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
