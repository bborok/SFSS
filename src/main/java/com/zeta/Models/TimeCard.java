package com.zeta.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * com.zeta.Models.TimeCard class
 */
public class TimeCard {

    private String username;
    private long shiftId;
    private Campus campus;
    private String location;
    private String notes;
    private List<Task> tasks;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar date;
    private String SPTotal;
    private String TPTotal;
    private String PCTotal;
    private String SWTotal;
    private String HSRTotal;
    private String ASTotal;

    public TimeCard() {
        tasks = new ArrayList<>();
    }

    public TimeCard(String username, long shiftId, Campus campus, String location, String notes, List<Task> tasks) {
        this.username = username;
        this.shiftId = shiftId;
        this.campus = campus;
        this.location = location;
        this.notes = notes;
        this.tasks = tasks;
    }

    public TimeCard(Campus campus, String location, String notes, List<Task> tasks, Calendar date,
                    String SPTotal, String TPTotal, String PCTotal,
                    String SWTotal, String HSRTotal, String ASTotal) {

        this.campus = campus;
        this.location = location;
        this.notes = notes;
        this.tasks = tasks;
        this.date = date;
        this.SPTotal = SPTotal;
        this.TPTotal = TPTotal;
        this.PCTotal = PCTotal;
        this.SWTotal = SWTotal;
        this.HSRTotal = HSRTotal;
        this.ASTotal = ASTotal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void addToTasks(Task task) {
        tasks.add(task);
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getSPTotal() {
        return SPTotal;
    }

    public void setSPTotal(String SPTotal) {
        this.SPTotal = SPTotal;
    }

    public String getTPTotal() {
        return TPTotal;
    }

    public void setTPTotal(String TPTotal) {
        this.TPTotal = TPTotal;
    }

    public String getPCTotal() {
        return PCTotal;
    }

    public void setPCTotal(String PCTotal) {
        this.PCTotal = PCTotal;
    }

    public String getSWTotal() {
        return SWTotal;
    }

    public void setSWTotal(String SWTotal) {
        this.SWTotal = SWTotal;
    }

    public String getHSRTotal() {
        return HSRTotal;
    }

    public void setHSRTotal(String HSRTotal) {
        this.HSRTotal = HSRTotal;
    }

    public String getASTotal() {
        return ASTotal;
    }

    public void setASTotal(String ASTotal) {
        this.ASTotal = ASTotal;
    }
}