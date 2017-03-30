package com.pingus.vent.Model;

/**
 * Created by August on 3/29/2017.
 */

public class ChatGroup {
    private String name;
    public ChatGroup(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
