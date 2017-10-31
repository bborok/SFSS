package com.zeta.Models;

import java.sql.Date;
import java.sql.Timestamp;


public class Shift extends AbstractShift {
   private User user;

    public Shift() {
    }

    public Shift(Long id, String title, Timestamp start, Timestamp end, Campus campus, Date date, String location, String notes, String requiredTraining, User user) {
        super(id, title, start, end, campus, date, location, notes, requiredTraining);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}