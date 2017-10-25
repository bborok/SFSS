package com.zeta.Models;

import java.sql.Timestamp;

public class ShiftRaw {
    private Timestamp startTime;
    private Timestamp endTime;
    private String title;
    private String username;
    private Campus campus;

    public ShiftRaw() {
    }

    public ShiftRaw(Timestamp startTime, Timestamp endTime, String title, String username, Campus campus) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.username = username;
        this.campus = campus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
