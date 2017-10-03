package com.zeta.Models;

import javax.persistence.*;

/*
 * com.zeta.Models.User class
 */
public class User {
    private String sfuId;

    private String name;

    private String email;

    private Long phoneNumber;

    private Campus preferredCampus;

    private Long studentNumber;

    private Role role;




    //TODO: Uncomment and implement this
    //private List<User> contacts = new ArrayList<User>();





//    @Column(name = "AccountCode", nullable = true)
//    private Long accountCode;

    public User() {
    } //Required by JPA

    public User(String sfuId, Long studentNumber, String name, String email, Long phoneNumber, Role role, Campus preferredCampus) {
        this.sfuId = sfuId;
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.preferredCampus = preferredCampus;
    }

    public String getSfuId() {
        return sfuId;
    }

    public void setSfuId(String sfuId) {
        this.sfuId = sfuId;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        //TODO: Maybe a good idea (Prob not)? Temp fix for rendering JSP's.
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        //TODO: Not a good idea. Temp fix for rendering JSP's.
        return phoneNumber == null ? 0 : phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Campus getPreferredCampus() {
        return preferredCampus;
    }

    public void setPreferredCampus(Campus preferredCampus) {
        this.preferredCampus = preferredCampus;
    }
}