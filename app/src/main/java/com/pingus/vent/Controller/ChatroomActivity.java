package com.pingus.vent.Controller;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pingus.vent.Model.ChatGroup;
import com.pingus.vent.Model.ChatArrayAdapter;
import com.pingus.vent.Model.ChatMessage;
import com.pingus.vent.Model.User;
import com.pingus.vent.R;

public class ChatroomActivity extends AppCompatActivity {
    private DatabaseReference database;
    private FirebaseUser user;
    private EditText messageText;
    private String userName;
    private ListView messageList;
    private ChatArrayAdapter lvAdapter;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("CHATROOM_NAME"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        messageList = (ListView) findViewById(R.id.listChatRoom);
        messageList.setDivider(null);
        messageList.setDividerHeight(0);
        lvAdapter = new ChatArrayAdapter(this, R.layout.message_left);
        database = FirebaseDatabase.getInstance().getReference().child("chatroomlist").child(getIntent().getStringExtra("CHATROOM_NAME")).child("messages");
        user = FirebaseAuth.getInstance().getCurrentUser();
        database.getRoot().child("users").child(user.getUid()).child("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = (String) dataSnapshot.getValue();
                if (userName == null) {
                    database.getRoot().child("users").child(user.getUid()).setValue(new User("no_username"));
                }
                lvAdapter.setUsername(userName);
                lvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        messageList.setAdapter(lvAdapter);
        messageList.setVisibility(View.VISIBLE);
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendChatMessage(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendChatMessage(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        messageText = (EditText) findViewById(R.id.messageEditText);
    }

    public void send(View view) {
        ChatMessage message = new ChatMessage(messageText.getText().toString(), userName);
        message.setLeft(false);
        database.push().setValue(message);
        messageText.setText("");
    }
    private void appendChatMessage(DataSnapshot dataSnapshot) {
        ChatMessage cm = dataSnapshot.getValue(ChatMessage.class);
        if (cm == null) {
            return;
        }
        lvAdapter.add(cm);
        lvAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
