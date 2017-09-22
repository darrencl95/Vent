package com.pingus.vent.Model;
/**
 * Encapsulation of comments for each post
 * Created by smant on 7/23/2017.
 */

public class Comment {
    private String postId;
    private String username;
    private String timestamp;
    private int likes;
    private String body;
    private int profilePic;
    private int reports;

    public Comment(String postId, String username, String timestamp, int likes, String body, int profilePic, int reports) {
        this.postId = postId;
        this.username = username;
        this.timestamp = timestamp;
        this.likes = likes;
        this.body = body;
        this.profilePic = profilePic;
        this.reports = reports;
    }

    public String getPostId() {return postId;}

    public String getUsername() {return username;}

    public String getTimestamp() {return timestamp;}

    public int getLikes() {return likes;}

    public String getBody() {return body;}

    public int getProfilePic() {return profilePic;}

    public int getReports() {return reports;}
}
