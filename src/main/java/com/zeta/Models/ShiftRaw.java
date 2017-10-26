package com.zeta.Models;

import java.sql.Timestamp;

public class ShiftRaw extends AbstractShift {
    private String username;

    public ShiftRaw() {
    }

    public ShiftRaw(Long id, String title, Timestamp start, Timestamp end, Campus campus, String username) {
        super(id, title, start, end, campus);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
