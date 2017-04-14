package com.pingus.vent.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shantanu Mantri on 3/10/2017.
 * Chat messages
 */

public class ChatMessage implements Serializable{
    private String messageText;
    private String messageUser;
    private String userID;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser, String userID) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.userID = userID;
        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return messageUser + ": " + messageText;
    }
}
