package com.pingus.vent.Controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pingus.vent.Model.Post;
import com.pingus.vent.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PostActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private EditText text;
    private Button butt;
    private DatabaseReference database;
    private FirebaseUser user;
    private String name;
    private Integer profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post Creator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()) {
                    if(shot.getKey().equals("displayName")) {
                        name = shot.getValue().toString();
                    }
                    if(shot.getKey().equals("imagePath")) {
                        Long temp = (Long) shot.getValue();
                        profile = temp.intValue();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        text = (EditText) findViewById(R.id.post_comment);
        butt = (Button) findViewById(R.id.post_button);
        database = FirebaseDatabase.getInstance().getReference().child("wallpost");
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Post new comment to database
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String timestamp = dateFormat.format(date);
                Post post = new Post(generateID(), name, timestamp, 0, text.getText().toString(), profile, 0);
                database.push().setValue(post);
            }
        });
    }

    public static String generateID() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }
}
