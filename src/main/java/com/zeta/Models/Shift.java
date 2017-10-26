package com.zeta.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.stream.Location;
import java.sql.Timestamp;


public class Shift extends AbstractShift {
   private User user;

    public Shift() {
    }

    public Shift(Long id, String title, Timestamp start, Timestamp end, Campus campus, User user) {
        super(id, title, start, end, campus);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}