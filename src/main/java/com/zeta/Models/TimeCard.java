package com.zeta.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * com.zeta.Models.TimeCard class
 */
public class TimeCard {

    private List<Task> tasks = new ArrayList<Task>();
    private String notes;
    private Calendar startTime;
    private Calendar endTime;
    private Campus campus;
    private Date date;
    private String location;
    private String SPTotal;
    private String TPTotal;
    private String PCTotal;
    private String SWTotal;
    private String HSRTotal;
    private String ASTotal;

    public TimeCard(){}
    public TimeCard(List<Task> tasks, String notes, Calendar startTime, Calendar endTime, Campus campus,
                    Date date, String location, String SPTotal, String TPTotal, String PCTotal,
                    String SWTotal,String HSRTotal, String ASTotal) {
        this.tasks = tasks;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.campus = campus;
        this.date = date;
        this.location = location;
        this.SPTotal = SPTotal;
        this.TPTotal = TPTotal;
        this.PCTotal = PCTotal;
        this.SWTotal = SWTotal;
        this.HSRTotal = HSRTotal;
        this.ASTotal = ASTotal;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Date getDate(){return date;}

    public void setDate(Date date){this.date = date;}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void SWTotal(String SWTotal) {
        this.SWTotal = SWTotal;
    }

    public String getHSRTotal() {
        return HSRTotal;
    }

    public void HSRTotal(String HSRTotal) {
        this.HSRTotal = HSRTotal;
    }

    public String getASTotal() {
        return ASTotal;
    }

    public void ASTotal(String ASTotal) {
        this.ASTotal = ASTotal;
    }
}