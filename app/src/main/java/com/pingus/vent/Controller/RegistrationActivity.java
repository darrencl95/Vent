package com.pingus.vent.Controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pingus.vent.Model.User;
import com.pingus.vent.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private EditText editTextUsername;
    private EditText editTextDisplayName;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextDisplayName = (EditText) findViewById(R.id.editTextDisplayName);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * onclick method for register button
     * registers new user in database if inputs are valid
     * @param view
     */
    public void register(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String displayName = editTextDisplayName.getText().toString();
        if(email.equals("") || password.equals("")) {
            Toast.makeText(getBaseContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            return;
        }
        //if (!CredentialVerification.verifyEmail(email)) {
        //    Toast.makeText(getBaseContext(), "Invalid Email",
        //            Toast.LENGTH_SHORT).show();
        //    return;
        //}
        String message = CredentialVerification.verifyPassword(password);
        if (!message.isEmpty()) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(editTextPassword2.getText().toString())) {
            Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }
        String username = editTextUsername.getText().toString().trim();
        if (username.isEmpty() || username.length() < 4) {
            Toast.makeText(getBaseContext(), "Username too short", Toast.LENGTH_SHORT).show();
            return;
        }
        createAccount(email, password, displayName);
    }

    /**
     * creates new account
     * @param email the email of the new account
     * @param password the password of the new account
     */
    private void createAccount(String email, String password, final String displayName) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Vent", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                DatabaseReference myRef = database.getInstance().getReference();
                                User u = new User(editTextUsername.getText().toString().trim(), displayName);

                                myRef.child("users").child(user.getUid()).setValue(u);
                            }
                            Toast.makeText(getBaseContext(), "Registration succeeded",
                                    Toast.LENGTH_SHORT).show();
                            RegistrationActivity.super.onBackPressed();
                        } else {
                            Log.d("Firebase",task.getException().getMessage().toString());
                            Toast.makeText(getBaseContext(), "Registration failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}