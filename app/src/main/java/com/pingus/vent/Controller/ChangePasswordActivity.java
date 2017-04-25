package com.pingus.vent.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.pingus.vent.R;

import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by ishanwaykul on 4/25/17.
 */

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText entry;
    private EditText confirmEntry;
    private Button end;
    private FirebaseUser user;
    private DatabaseReference database;
    private TextView currentUsername;


    protected void onCreate(Bundle savedStateInstance) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        super.onCreate(savedStateInstance);
        setContentView(R.layout.change_password);
        entry = (EditText) findViewById(R.id.newPasswordEntry);
        confirmEntry = (EditText) findViewById(R.id.confirmnewPasswordEntry);
        end = (Button) findViewById(R.id.changePass);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entered = entry.getText().toString().trim();
                String confirmentered = confirmEntry.getText().toString().trim();
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                if(!entered.equals(confirmentered)) {
                    Toast.makeText(getBaseContext(),"Sorry, The Fields Do Not Match",Toast.LENGTH_SHORT).show();
                    return;
                }
                user.updatePassword(entered);
                startActivity(intent);
            }
        });
    }
}
