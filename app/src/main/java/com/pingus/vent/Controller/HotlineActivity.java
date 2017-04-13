package com.pingus.vent.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pingus.vent.R;

import java.util.ArrayList;

public class HotlineActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private ArrayList<String> hotlines;
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        //setup toolbar with back button and name
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hotline Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        reference = FirebaseDatabase.getInstance().getReference().child("state");
        hotlines = new ArrayList<>();
        final ListView lv = (ListView) findViewById(R.id.hotList);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()) {
                    hotlines.add(shot.getKey() + " " + shot.getValue().toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,hotlines);
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
