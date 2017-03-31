package com.pingus.vent.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    public void register(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        if(email.equals("") || password.equals("")) {
            Toast.makeText(getBaseContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!CredentialVerification.verifyEmail(email)) {
            Toast.makeText(getBaseContext(), "Invalid Email",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String message = CredentialVerification.verifyPassword(password);
        if (!message.isEmpty()) {
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(editTextPassword2.getText().toString())) {
            Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_SHORT);
            return;
        }
        createAccount(email, password);
    }
}
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Vent", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                DatabaseReference myRef = database.getReference(user.getUid());
                                User u = new User(editTextUsername.getText().toString().trim());
                                myRef.setValue(u);
                            }
                            Toast.makeText(getBaseContext(), "Registration succeeded",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Firebase",task.getException().getMessage().toString());
                            Toast.makeText(getBaseContext(), "Registration failed",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }