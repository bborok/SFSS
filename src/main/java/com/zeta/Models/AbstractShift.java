package com.zeta.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeta.Configurations.CustomDateTimeDeserializer;
import com.zeta.Configurations.CustomDateTimeSerializer;

import java.util.Date;

public abstract class AbstractShift {
    private Long id;
    private String title;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private Date start;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private Date end;

    private Campus campus;

    private Date date;
    private String location;
    private String notes;
    private String requiredTraining;

    public AbstractShift() {
    }

    public AbstractShift(Long id, String title, Date start, Date end, Campus campus, Date date, String location, String notes, String requiredTraining) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.campus = campus;
        this.date = date;
        this.location = location;
        this.notes = notes;
        this.requiredTraining = requiredTraining;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.date = start; //TODO: This is a HACK
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

    @Override
    public String toString() {
        return "AbstractShift{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", campus=" + campus +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                ", requiredTraining='" + requiredTraining + '\'' +
                '}';
    }
}
