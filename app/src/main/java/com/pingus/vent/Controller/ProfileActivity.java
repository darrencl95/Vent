package com.pingus.vent.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pingus.vent.Model.ChatArrayAdapter;
import com.pingus.vent.Model.ChatMessage;
import com.pingus.vent.Model.User;
import com.pingus.vent.R;
import com.pingus.vent.R;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference database;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference().child("chatroomlist").child(getIntent().getStringExtra("CHATROOM_NAME")).child("messages");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = FirebaseAuth.getInstance().getCurrentUser();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeUsername(String username) {
        database.getRoot().child("users").child(user.getUid()).child("userName").setValue(username);

    }

    public void changePassword(String pswd) {
        user.updatePassword(pswd);
    }

}
