package com.pingus.vent.Model;

/**
 * Created by Darren on 3/5/2017.
 * The backend for the user class. Only used within  the app, gets info from database
 */

public class User {

    // implement user image
    private String userName;
    private Integer imagePath;
    private String displayName;
    public User() {

    }
    public User(String userName, String displayName) {
        this.userName = userName;
        this.displayName = displayName;
        imagePath = 0;
    }

    /**
     * Gets the username of the user
     * @return String the username of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the display name of the user
     * @return String the display name of the user
     */
    public String getDisplayName() {return displayName;}

    /**
     * Gets the image path of the user profile
     * @return the image path in String
     */
    public Integer getImagePath() {
        return imagePath;
    }

    /**
     * Sets the image path
     * @param path the path to the image
     */
    public void setImagePath(Integer path) {
        imagePath = path;
    }
}
