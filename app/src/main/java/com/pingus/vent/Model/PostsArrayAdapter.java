package com.pingus.vent.Model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.IconType;
import com.like.LikeButton;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.pingus.vent.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * ArrayAdapter for Posts Objects, signals proper placement on layout, etc.
 * Created by Shantanu Mantri on 6/27/2017.
 */

public class PostsArrayAdapter  extends RecyclerView.Adapter<PostsArrayAdapter.MyViewHolder> {
    private ArrayList<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, timestamp, postComment, numLikes, bold;
        public CircularImageView profilePic;
        public Button commentButton;
        public LikeButton likeButton;
        private String currentUserDisplayName;

        public MyViewHolder(View view) {
            super(view);
            profilePic = (com.mikhaellopez.circularimageview.CircularImageView) view.findViewById(R.id.prof_pic);
            username = (TextView) view.findViewById(R.id.user_name);
            timestamp = (TextView) view.findViewById(R.id.time_stamp);
            postComment = (TextView) view.findViewById(R.id.comment_body);
            numLikes = (TextView) view.findViewById(R.id.num_likes);
            likeButton = (LikeButton) view.findViewById(R.id.like_button);
            commentButton = (Button) view.findViewById(R.id.comment_button);
            bold = (TextView) view.findViewById(R.id.bold);
            currentUserDisplayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
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
        final String currentID = currentPost.getId();
        holder.profilePic.setImageResource(currentPost.getProf_pic());

        holder.username.setText(currentPost.getUsername());

        holder.timestamp.setText(currentPost.getTimestamp());

        holder.postComment.setText(currentPost.getComment());

        final LikeButton liker = holder.likeButton;
        final Button commenter = holder.commentButton;
        final TextView numLikes = holder.numLikes;
        final String from_user = holder.currentUserDisplayName;
        liker.setIcon(IconType.Heart);
        numLikes.setText(String.valueOf(currentPost.getLikes()));

        liker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(liker.isLiked()) {
                    liker.setLiked(false);
                    numLikes.setText(String.valueOf(currentPost.getLikes()));
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("wallpost");
                    //TODO Access database to change value
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot shot : dataSnapshot.getChildren()) {
                                if(shot.getKey().equals(currentID)) {
                                    int likes = (Integer) shot.child("likes").getValue();
                                    likes--;
                                    shot.child("likes").getRef().setValue(likes);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    final DatabaseReference newref = FirebaseDatabase.getInstance().getReference().child("notifications");
                    newref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot shot : dataSnapshot.getChildren()) {
                                if(shot.child("post_id").equals(currentID)) {
                                    //delete this reference
                                    String id = shot.getKey();
                                    newref.child(id).removeValue();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //TODO Access database to kill notification

                } else {
                    liker.setLiked(true);
                    numLikes.setText(String.valueOf(currentPost.getLikes() + 1));
                    //TODO Access database to change value
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("wallpost");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot shot : dataSnapshot.getChildren()) {
                                if(shot.getKey().equals(currentID)) {
                                    int likes = (Integer) shot.child("likes").getValue();
                                    likes++;
                                    shot.child("likes").getRef().setValue(likes);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
                    //Access database to add notification
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    String timestamp = dateFormat.format(date);
                    String id = generateID();
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("notifications");
                    Notification not = new Notification(id, currentPost.getUsername(), from_user, true, "", timestamp, currentID);
                    database.push().setValue(not);
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

    public static String generateID() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }
}
