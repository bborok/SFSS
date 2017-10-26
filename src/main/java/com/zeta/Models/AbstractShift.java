package com.zeta.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public abstract class AbstractShift {
    private Long id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Timestamp start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Timestamp end;
    private Campus campus;
//    private User user;

    public AbstractShift() {
    }

    public AbstractShift(Long id, String title, Timestamp start, Timestamp end, Campus campus) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.campus = campus;
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
