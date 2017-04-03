package com.pingus.vent.Controller;

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
 * This class represents everything that is going on in the login page
 * Created by Darren Leung on 3/6/2017.
 * Global ID:    Username: user@gatech.edu Password: Password100
 */
public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    // declare UI components
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginBtn;
    private Button registerBtn;
    private ImageView logo;
    private ImageView background;
    private TextView forgotpw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        registerBtn = (Button) findViewById(R.id.regbutton);
        logo = (ImageView) findViewById(R.id.vicon);
        background = (ImageView) findViewById(R.id.vbg);
        forgotpw = (TextView) findViewById(R.id.fpw);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Vent", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Vent", "onAuthStateChanged:signed_out");
                }
            }
        };


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if(email.equals("") || password.equals("")) {
                    Toast.makeText(getBaseContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                signIn(email, password);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void signIn(String email, String password) {
        if(email.equals("") || password.equals("")) {
            Toast.makeText(getBaseContext(), "Email or Password Invalid", Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Pickup", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Toast.makeText(getBaseContext(), "Authentication succeeded",
                                    Toast.LENGTH_SHORT).show();
                            Intent homePage = new Intent(getBaseContext(),MainActivity.class);
                            startActivity(homePage);
                        } else {
                            Log.w("Pickup", "signInWithEmail:failed", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}