package com.pingus.vent.Model;

/**
 * Created by Darren on 3/5/2017.
 */

public class User {

    // implement user image
    private String userName;
    private String imagePath;

    public User(String userName) {
        this.userName = userName;
        imagePath = "";
    }
    public String getUserName() {
        return userName;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String path) {
        imagePath = path;
    }
}
