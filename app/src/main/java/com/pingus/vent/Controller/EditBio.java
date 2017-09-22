package com.pingus.vent.Controller;

/**
 * Created by ishanwaykul on 5/24/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.pingus.vent.R;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

public class EditBio extends AppCompatActivity {
    private EditText biodescription;
    private Button confirm_button;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.biochange);
        biodescription = findViewById(R.id.myBio);
        confirm_button = (Button) findViewById(R.id.biochange);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_bio = biodescription.getText().toString().trim();
                if (new_bio.length() != 0) {
                    change_bio(biodescription.getText().toString().trim());
                }

            }
        });


    }

    public void change_bio(String bio_text) {
        //set the user's bio to the new bio requested
        //1. access the user's current bio
        //2. change it with the text written by the user

    }
}