package com.zeta.Models;

import java.util.Date;

public class Announcement {
    private String username;
    private String title;
    private String message;
    private Date date;
    private Campus campus;
//    private int id;

    public Announcement() {
    }

    // TODO: Remove this constructor once campus and id are linked with frontend
//    public Announcement(String username, String title, String message, Date date, Campus campus) {
//        this.username = username;
//        this.title = title;
//        this.message = message;
//        this.date = date;
//        this.campus = campus;
//    }

    public Announcement(String username, String title, String message, Date date, Campus campus) {
        this.username = username;
        this.title = title;
        this.message = message;
        this.date = date;
        this.campus = campus;
//        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}