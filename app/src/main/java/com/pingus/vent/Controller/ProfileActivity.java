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
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pingus.vent.R;
import com.pingus.vent.Model.User;

import static com.pingus.vent.R.id.viewFriends;


public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference database;
    private String email;
    private TextView currentUsername;
    private ImageView profilePicture;
    private Button changepwd;
    private Button accessFriendList;

    private Button userChange;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_page);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accessFriendList = (Button) findViewById(R.id.viewFriends);
        changepwd = (Button) findViewById(R.id.changePassword);
        userChange = (Button) findViewById(R.id.changeUsername);
        accessFriendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FriendlistActivity.class);
                startActivity(intent);
            }
        }) ;
        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), ChangePasswordActivity.class);
                startActivity(intent2);
            }
        }) ;
        userChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), ChangeUsernameActivity.class);
                startActivity(intent4);
            }
        }) ;

        //database = FirebaseDatabase.getInstance().getReference().child("chatroomlist").child(getIntent().getStringExtra("CHATROOM_NAME")).child("messages");

        user = FirebaseAuth.getInstance().getCurrentUser();
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
        currentUsername = (TextView) findViewById(R.id.currentUsername);
        currentUsername.setText(username);
    }

    public void changePassword(String pswd) {
        user.updatePassword(pswd);
    }



};

