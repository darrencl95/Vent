package com.pingus.vent.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.pingus.vent.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pingus.vent.R.id.toolbar;

public class CounselingActivity extends AppCompatActivity {
    private DatabaseReference database;
    private ArrayList<String> colleges;
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        //get toolbar//
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Campus Resource Information");
        //setup toolbar with back button and name
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Campus Resource Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //end toolbar changes//
        //make arraylist
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        colleges = new ArrayList<>();
        //get college database and set up names to add to list
        database = FirebaseDatabase.getInstance().getReference().child("college");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()) {
                    Set<String> set = new HashSet<String>();
                    String ans = shot.getKey();
                    //adding all colleges to the list
                    set.add(ans);
                    colleges.addAll(set);
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(CounselingActivity.this,android.R.layout.simple_spinner_item,colleges);
                spinner.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long arg3) {
                String child = (String) arg0.getAdapter().getItem(arg2);
                if (child == null || child.equals("...")) {
                    return;
                }
                database = FirebaseDatabase.getInstance().getReference().child("college").child(child);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashSet<String> set = new HashSet<String>();
                        int a = 0;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if(snapshot.getKey().equals("counseling")) {
                                set.add("Counseling: " + snapshot.getValue().toString());
                            } else {
                                set.add("Police: " + snapshot.getValue().toString());
                            }
                        }
                        resetC(set);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }
    /*
    Resets textview of counselor/police number
     */
    private void resetC(HashSet<String> value) {
        ListView list = (ListView) findViewById(R.id.spinlist2);
        ArrayList<String> p = new ArrayList<>();
        p.addAll(value);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getBaseContext(),android.R.layout.simple_list_item_1,p);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}