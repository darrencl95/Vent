package com.pingus.vent.Model;

/**
 * Created by Darren on 3/5/2017.
 */

public class User {

    // implement user image
    private String displayName;
    private String firstName;
    private String lastName;
    private String userType;

    public User(String user, String first, String last) {
        displayName = user;
        firstName = first;
        lastName = last;
        userType = "user";
    }

    public String getUsername() {
        return displayName;
    }

    public void setUsername(String username) {
        this.displayName = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
