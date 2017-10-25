package com.zeta.Models;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/*
 * com.zeta.Models.Shift class
 */
public class Shift {

    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Timestamp start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Timestamp end;
    private Campus campus;
    private User user;

    public Shift() {
    }


    public Shift(Timestamp startTime, Timestamp endTime, String title, User user, Campus campus) {
        this.title = title;
        this.start = startTime;
        this.end = endTime;
        this.campus = campus;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}