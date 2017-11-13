package com.zeta.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Announcements {
    private String username;
    private String title;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private Campus campus;
    private int id;

    public Announcements() {

    }

    public Announcements (String username, String title, String message, Date date, Campus campus, int id) {
        this.username = username;
        this.title = title;
        this.message = message;
        this.date = date;
        this.campus = campus;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { this.username = username;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title;}

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) { this.message = message;}

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) { this.date = date;}

    public Campus getCampus() {
        return campus;
    }
    public void setCampus(Campus campus) { this.campus = campus;}

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}
}
