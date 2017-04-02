package com.pingus.vent.Controller;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import com.pingus.vent.Model.ChatMessage;
import com.pingus.vent.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ChatroomActivity extends AppCompatActivity {
    private DatabaseReference database;
    private FirebaseUser user;
    private EditText messageText;
    private ArrayList<ChatMessage> messages;
    private String userName;
    private ListView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        messages = new ArrayList<>();
        messageList = (ListView) findViewById(R.id.listChatRoom);
        final ArrayAdapter<ChatMessage> lvAdapter = new ArrayAdapter<ChatMessage>(
                this, android.R.layout.simple_list_item_1, messages
        );
        messageList.setAdapter(lvAdapter);
        setTitle(getIntent().getStringExtra("CHATROOM_NAME"));
        database = FirebaseDatabase.getInstance().getReference().child(getIntent().getStringExtra("CHATROOM_NAME"));
        user = FirebaseAuth.getInstance().getCurrentUser();
        database.getRoot().child("users").child(user.getUid()).child("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = (String) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendChatMessage(dataSnapshot);
                lvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendChatMessage(dataSnapshot);
                lvAdapter.notifyDataSetChanged();
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
        database.push().setValue(message);
        messageText.setText("");
    }
    private void appendChatMessage(DataSnapshot dataSnapshot) {
        messages.add(dataSnapshot.getValue(ChatMessage.class));
    }
}
