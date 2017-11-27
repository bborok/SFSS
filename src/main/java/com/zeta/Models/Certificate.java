package com.zeta.Models;

import java.util.Date;

public class Certificate {

    private String name;
    private String level;
    private Integer number;
    private Date expirationDate;

    public Certificate() {
    }

    public Certificate(String name, String level, Integer number, Date expirationDate) {
        this.name = name;
        this.level = level;
        this.number = number;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}