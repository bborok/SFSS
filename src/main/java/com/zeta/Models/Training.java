package com.zeta.Models;

import java.util.Date;

public class Training {
    private String task;
    private int hours;
    private Date dateCertified;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Date getDateCertified() {
        return dateCertified;
    }

    public void setDateCertified(Date dateCertified) {
        this.dateCertified = dateCertified;
    }
}
