package com.pingus.vent.Model;

/**
 * Created by August on 3/29/2017.
 */

public class ChatGroup {
    private String name;
    private ChatType type;
    private String creatorID;
    public ChatGroup() {

    }
    public ChatGroup(String name, ChatType type, String creatorID) {
        this.name = name;
        this.type = type;
        this.creatorID = creatorID;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }

    public ChatType getType() {
        return type;
    }

}
