package com.pingus.vent.Model;

/**
 * Created by August on 3/29/2017.
 */

public class ChatGroup {
    private String name;
    private ChatType type;
    public ChatGroup() {

    }
    public ChatGroup(String name, ChatType type) {
        this.name = name;
        this.type = type;
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
