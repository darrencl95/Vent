package com.pingus.vent.Model;

import java.io.Serializable;

/**
 * Created by August on 3/29/2017.
 */
@SuppressWarnings("serial")
public class ChatGroup implements Serializable{
    private String name;
    private ChatType type;
    private String creatorID;
    private ChatMessage recent;
    public ChatGroup() {

    }
    public ChatGroup(String name, ChatType type, String creatorID) {
        this.name = name;
        this.type = type;
        this.creatorID = creatorID;
        recent = null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

    public ChatMessage getRecentCM() {
        return recent;
    }

    public void setRecent(ChatMessage newCM) {
        recent = newCM;
    }

    public String getCreatorID() {
        return creatorID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ChatGroup)) {
            return false;
        }
        ChatGroup cg = (ChatGroup) o;
        return name.equals(cg.getName()) && cg.getType() == getType() && cg.getCreatorID() == creatorID;
    }
}
