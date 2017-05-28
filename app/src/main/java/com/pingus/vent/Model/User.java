package com.pingus.vent.Model;

/**
 * Created by Darren on 3/5/2017.
 * The backend for the user class. Only used within  the app, gets info from database
 */

public class User {

    // implement user image
    private String userName;
    private String imagePath;
    public User() {

    }
    public User(String userName) {
        this.userName = userName;
        imagePath = "";
    }

    /**
     * Gets the username of the user
     * @return String the name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the image path of the user profile
     * @return the image path in String
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the image path
     * @param path the path to the image
     */
    public void setImagePath(String path) {
        imagePath = path;
    }
}
