package com.zeta.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeta.Configurations.JsonDeserializers.CustomDateDeserializer;
import com.zeta.Configurations.JsonDeserializers.CustomEmptyStringToNullDeserializer;
import com.zeta.Configurations.JsonSerializers.CustomDateSerializer;
import com.zeta.Configurations.JsonDeserializers.CustomDateTimeDeserializer;
import com.zeta.Configurations.JsonSerializers.CustomDateTimeSerializer;

import java.util.Date;

public class Shift {
    private Long id;
    private String title;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private Date start;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private Date end;

    private Campus campus;

    private String location;
    private String notes;

    @JsonDeserialize(using = CustomEmptyStringToNullDeserializer.class)

    private String requiredTraining;
    private boolean isTimeCardSubmitted = false; //By default is false
    private String username;
    private ConfirmationStatus confirmationStatus;

    public Shift() {
    }

    public Shift(Long id, String title, Date date, Date start, Date end, Campus campus, String location, String notes, String requiredTraining, boolean isTimeCardSubmitted, String username, ConfirmationStatus confirmationStatus) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.start = start;
        this.end = end;
        this.campus = campus;
        this.location = location;
        this.notes = notes;
        this.requiredTraining = requiredTraining;
        this.isTimeCardSubmitted = isTimeCardSubmitted;
        this.username = username;
        this.confirmationStatus = confirmationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
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

    public String getRequiredTraining() {
        return requiredTraining;
    }

    public void setRequiredTraining(String requiredTraining) {
        this.requiredTraining = requiredTraining;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsTimeCardSubmitted() {
        return isTimeCardSubmitted;
    }

    public void setTimeCardSubmitted(boolean timeCardSubmitted) {
        isTimeCardSubmitted = timeCardSubmitted;
    }

    public ConfirmationStatus getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(ConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", start=" + start +
                ", end=" + end +
                ", campus=" + campus +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                ", requiredTraining='" + requiredTraining + '\'' +
                ", isTimeCardSubmitted=" + isTimeCardSubmitted +
                ", username='" + username + '\'' +
                ", confirmationStatus=" + confirmationStatus +
                '}';
    }
}
