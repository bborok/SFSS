package com.zeta.Models;

public class Certificate {

    private String name;
    private String level;
    private Integer number;

    public Certificate(String name, String level, Integer number) {
        this.name = name;
        this.level = level;
        this.number = number;
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
}