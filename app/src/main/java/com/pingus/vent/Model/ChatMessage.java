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

    /**
     * Gets the text content of the message
     * @return String the text
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets the text content of the message
     * @param messageText String the message
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Gets the user of the message
     * @return String the username of the user
     */
    public String getMessageUser() {
        return messageUser;
    }

    /**
     * Links the user to the message he/she sent
     * @param messageUser
     */
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    /**
     * Gets the message time
     * @return long the time the message was sent
     */
    public long getMessageTime() {
        return messageTime;
    }

    /**
     * Sets the time of the message
     * @param messageTime time the message was sent
     */
    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    /**
     * Gets the user id for the message
     * @return String the userID
     */
    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return messageUser + ": " + messageText;
    }
}
