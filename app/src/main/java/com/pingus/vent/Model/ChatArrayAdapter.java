package com.pingus.vent.Model;

/**
 * Created by August on 4/2/2017.
 * Custom ArrayAdapter for chat
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pingus.vent.Controller.ChatroomActivity;
import com.pingus.vent.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private TextView userText;
    private TextView dateText;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private Context context;
    private String username;

    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }
    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.chatMessageList.size();
    }

    @Override
    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        if(chatMessageObj == null) {
            return row;
        }
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(chatMessageObj != null && username!= null && !chatMessageObj.getMessageUser().equals(username)) {
            row = inflater.inflate(R.layout.message_left, parent, false);
        } else {
            row = inflater.inflate(R.layout.message_right, parent, false);
        }
        //set username and message text
        chatText = (TextView) row.findViewById(R.id.msgr);
        chatText.setText(chatMessageObj.getMessageText());
        userText = (TextView) row.findViewById(R.id.msgu);
        userText.setText(chatMessageObj.getMessageUser());
        //format timsetamp string
        Date date = new Date(chatMessageObj.getMessageTime());
        String time = date.toString();
        Date curr = new Date();
        String hr = " AM";
        int hour = Integer.parseInt(time.substring(11,13));
        if (hour > 12) {
            hour = hour - 12;
            hr = " PM";
        } else if (hour == 0) {
            hour = 12;
        }
        //don't modify this lol
        if (curr.getDate() - date.getDate() >= 7) {
            time = time.substring(4, 10)+ ", " + hour + "" + time.substring(13, 16) + hr;
        } else if ((curr.getDay() - date.getDay() < 7 && curr.getDay() - date.getDay() > 0)
                || (curr.getDay() - date.getDay() == 0 && (curr.getHours() - date.getHours() > 1
                || (curr.getMinutes() >= date.getMinutes() && curr.getHours() - date.getHours() > 0)))){
            time = time.substring(0, 3) + " " + hour + "" + time.substring(13, 16) + hr;
        } else if ((curr.getHours() - date.getHours() == 1 && curr.getMinutes() < date.getMinutes()
                    && curr.getMinutes() + date.getMinutes() > 1) ||
                    curr.getMinutes() - date.getMinutes() < 60 && curr.getMinutes() - date.getMinutes() >= 1) {
            int min = curr.getMinutes() - date.getMinutes();
            if (curr.getHours() > date.getHours()) {
                min = 60 - date.getMinutes() + curr.getMinutes();
            }
            time = min + " min";
        } else if (curr.getMinutes() - date.getMinutes() < 1) {
            time = "Just now";
        }
        //set timestamp text
        dateText = (TextView)row.findViewById(R.id.timeText);
        dateText.setText(time);
        return row;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}