package com.pingus.vent.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.vision.text.Text;
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


/**
 * Created by Ishan Waykul
 * The main profile activity for all users
 */
public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference database;
    private ImageView profPic;
    private TextView userName;
    private TextView bio;
    private Button editProfile;
    private android.support.v7.widget.Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_page);
        profPic = (ImageView) findViewById(R.id.ivUserProfilePhoto);
        userName = (TextView) findViewById(R.id.textView3);
        bio = (TextView) findViewById(R.id.textView6);
        editProfile = (Button) findViewById(R.id.button2);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this,"Now going to edit profile activity",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        }) ;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.getDisplayName() != null) {
            userName.setText(user.getDisplayName());
        }
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
}

