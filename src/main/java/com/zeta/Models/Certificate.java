package com.zeta.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeta.Configurations.JsonDeserializers.CustomDateDeserializer;
import com.zeta.Configurations.JsonSerializers.CustomDateSerializer;

import java.util.Date;

public class Certificate {

    private String name;
    private Integer level;
    private String id;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date expirationDate;

    public Certificate() {
    }

    public Certificate(String name, Integer level, String id, Date expirationDate) {
        this.name = name;
        this.level = level;
        this.id = id;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}