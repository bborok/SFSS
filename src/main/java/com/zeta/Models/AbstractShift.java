package com.zeta.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeta.Configurations.CustomTimestampDeserializer;
import com.zeta.Configurations.CustomTimestampSerializer;

import java.sql.Date;
import java.sql.Timestamp;

public abstract class AbstractShift {
    private Long id;
    private String title;

    @JsonSerialize(using = CustomTimestampSerializer.class)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    private Timestamp start;

    @JsonSerialize(using = CustomTimestampSerializer.class)
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    private Timestamp end;

    private Campus campus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private String location;
    private String notes;
    private String requiredTraining;

    public AbstractShift() {
    }

    public AbstractShift(Long id, String title, Timestamp start, Timestamp end, Campus campus, Date date, String location, String notes, String requiredTraining) {
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

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
        //set this.date to the Timestamp's date.
        this.date = new Date(start.getTime());
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
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
                '}';
    }
}
