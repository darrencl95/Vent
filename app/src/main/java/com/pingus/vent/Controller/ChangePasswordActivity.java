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

import java.util.regex.Pattern;

/**
 * Created by ishanwaykul on 4/25/17.
 * Changes the user's password via the profile page
 */

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText entry;
    private EditText confirmEntry;
    private Button end;
    private FirebaseUser user;
    private DatabaseReference database;
    private TextView currentUsername;
    private static final Pattern p = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z" +
            "0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x07\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b" +
            "\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(gatech.edu|GATECH.EDU)");


    protected void onCreate(Bundle savedStateInstance) {

        super.onCreate(savedStateInstance);
        setContentView(R.layout.change_password);
        user = FirebaseAuth.getInstance().getCurrentUser();
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
