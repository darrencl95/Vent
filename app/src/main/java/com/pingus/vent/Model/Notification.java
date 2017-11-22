package com.pingus.vent.Model;

/**
 * Created by Shantanu Mantri on 11/22/2017.
 * This class contains information for notifications
 */

public class Notification {
    private String id; //id of the notification
    private String to_id; //display name of user who posted
    private String from_id; //display name of user who liked/commented
    private boolean isLike; //check to see if it's a like or a comment
    private String comment; //if it is a comment, we must save this
    private String timestamp; //time liked
    private String post_id; //ID of the post the notification is referencing

    /**
     * Constructor for Notification
     * @param id The id of the notification
     * @param to_id Original poster
     * @param from_id Person who commented/liked
     * @param isLike Notification is either a comment or a like
     * @param comment body of notification comment
     * @param timestamp the time the notification was posted
     * @param post_id the post the notification is referencing
     */
    public Notification(String id, String to_id, String from_id, boolean isLike, String comment, String timestamp, String post_id) {
        this.id = id;
        this.to_id = to_id;
        this.from_id = from_id;
        this.isLike = isLike;
        this.comment = comment;
        this.timestamp = timestamp;
        this.post_id = post_id;
    }

    /**
     * Gets the post ID that was referenced in the notification
     * @return String the post ID
     */
    public String getPostID() { return post_id; }

    /**
     * Gets the ID from a notification
     * @return String the id of the notification
     */
    public String getID() { return id; }
    /**
     * Gets the display name of person who posted
     * @return String the displayName of user who posted
     */
    public String getToID() { return to_id; }

    /**
     * Gets the display name of person who liked/commented
     * @return String the displayName of user who liked/commented
     */
    public String getFromID() { return from_id; }

    /**
     * Gets the time the like/comment was made
     * @return String the time the comment was posted
     */
    public String getTime() { return timestamp; }

    /**
     * Identifies if it's a like or a comment
     * @return boolean if notification is like or not (comment)
     */
    public boolean isLike() { return isLike; }

    /**
     * Gets the comment the user posted
     * @return String a comment the user posted
     */
    public String getComment() { return comment; }
}
