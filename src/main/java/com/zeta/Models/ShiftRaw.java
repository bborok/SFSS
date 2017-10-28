package com.zeta.Models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * What is this class for? This class represent a row on the Shift table.
 *
 * The reason why this class was created
 * was because the typical Shift class has field of type User. However, to populate
 * the Shift's User field would require joining the Shift table and User table.
 *
 * Thus I (Patrick) created this class just as a representation of a single row on the Shift table.
 *
 * TLDR; ShiftRaw has a String username field, Shift has a User field
 */
public class ShiftRaw extends AbstractShift {
    private String username;

    public ShiftRaw() {
    }

    public ShiftRaw(Long id, String title, Timestamp start, Timestamp end, Campus campus, Date date, String location, String notes, String requiredTraining, String username) {
        super(id, title, start, end, campus, date, location, notes, requiredTraining);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ShiftRaw{" +
                "username='" + username + '\'' +
                '}';
    }
}
