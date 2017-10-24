package com.zeta.Models;

import java.util.Calendar;
import java.util.List;

/*
 * com.zeta.Models.TimeCard class
 */
public class TimeCard {

    private String user;
    private long shiftId;
    private Campus campus;
    private String location;
    private String notes;
    private List<Task> tasks;

    // there are probably more private fields that time card needs


    public TimeCard(String user, long shiftId, Campus campus, String location, String notes, List<Task> tasks) {
        this.user = user;
        this.shiftId = shiftId;
        this.campus = campus;
        this.location = location;
        this.notes = notes;
        this.tasks = tasks;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getShiftId() {
        return shiftId;
    }

    public void setShiftId(long shiftId) {
        this.shiftId = shiftId;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}