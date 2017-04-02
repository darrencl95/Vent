package com.pingus.vent.Model;

/**
 * Created by August on 4/2/2017.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pingus.vent.Controller.ChatroomActivity;
import com.pingus.vent.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private TextView userText;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private Context context;
    private boolean left;

    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(chatMessageObj.isLeft()) {
            row = inflater.inflate(R.layout.message_left, parent, false);
        } else {
            row = inflater.inflate(R.layout.message_right, parent, false);
        }
        chatText = (TextView) row.findViewById(R.id.msgr);
        chatText.setText(chatMessageObj.getMessageText());
        userText = (TextView) row.findViewById(R.id.msgu);
        userText.setText(chatMessageObj.getMessageUser());
        return row;
    }
    public void setLeft(boolean isLeft) {
        left = isLeft;
    }
}