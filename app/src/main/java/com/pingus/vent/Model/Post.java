package com.pingus.vent.Model;

/**
 * A basic class to encapsulate the Post data from the database
 * Created by Shantanu Mantri on 6/27/2017.
 */

public class Post {
    private String id;
    private String username;
    private String timestamp;
    private int likes;
    private String comment;
    private int prof_pic;
    private int reports;

    public Post(String id, String username, String timestamp, int likes, String comment, int prof_pic, int reports) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
        this.likes = likes;
        this.comment = comment;
        this.prof_pic = prof_pic;
        this.reports = reports;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getLikes() {return likes;}

    public String getComment() {
        return comment;
    }

    public int getProf_pic() {
        return prof_pic;
    }

    public int getReports() {
        return reports;
    }
}
