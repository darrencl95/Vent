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


public class EditProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference database;
    private String email;
    private TextView currentUsername;
    private TextView currentEmail;
    private TextView currentBio;
    private ImageView profilePicture;
    private Button changepwd;
    private Button accessFriendList;

    private Button userChange;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        accessFriendList = (Button) findViewById(R.id.viewFriends);
        changepwd = (Button) findViewById(R.id.buttonPassword);
        userChange = (Button) findViewById(R.id.buttonUsername);
        profilePicture = (ImageView) findViewById(R.id.ivUserProfilePhoto);
        currentUsername = (TextView) findViewById(R.id.textViewUsername);
        currentEmail = (TextView) findViewById(R.id.textViewEmail);
        currentBio = (TextView) findViewById(R.id.textViewBio);
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
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.getDisplayName() != null) {
            currentUsername.setText(user.getDisplayName());
        }
        currentEmail.setText(user.getEmail());
        //TODO set up the bio page in the database
        //currentBio.setText(user.getBio());
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
        currentUsername.setText(username);
    }
    public void changePassword(String pswd) {
        user.updatePassword(pswd);
    }
}