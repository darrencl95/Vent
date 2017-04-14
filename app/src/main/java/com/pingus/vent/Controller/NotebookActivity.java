package com.pingus.vent.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pingus.vent.R;

/**
 * Created by Darren on 4/8/2017.
 */

public class NotebookActivity extends AppCompatActivity {

    private EditText Title;
    private EditText Content;
    private Button saveButton;
    private DatabaseReference database;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        Title = (EditText) findViewById(R.id.noteTitle);

        if (getIntent().getStringExtra("NOTE_TITLE") != null) {
            Title.setText(getIntent().getStringExtra("NOTE_TITLE"));
        }

        Content = (EditText) findViewById(R.id.noteContent);

        if (getIntent().getStringExtra("NOTE_CONTENT") != null) {
            Content.setText(getIntent().getStringExtra("NOTE_CONTENT"));
        }

        saveButton = (Button) findViewById(R.id.saveButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("notes");


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.push();
                myRef.child("Title").setValue(Title.getText().toString());
                myRef.child("Content").setValue(Content.getText().toString());
            }
        });
    }



    // TODO: Adding notes
}