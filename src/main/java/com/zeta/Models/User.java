package com.zeta.Models;

import javax.persistence.*;

/*
 * com.zeta.Models.User class
 */
@Entity
public class User {
    @Id
    @Column(nullable = false, length = 30)
    private String sfuId;

    @Column(name = "StudentNumber",nullable = true, unique = true, length = 100)
    private Long studentNumber;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "PhoneNumber", nullable = false)
    private Long phoneNumber;

    //TODO: Uncomment and implement this
    //private List<User> contacts = new ArrayList<User>();

    @Enumerated(EnumType.STRING)
    @Column(name = "Role", nullable = false, length = 15)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "PreferredCampus", nullable = true, length = 15)
    private Campus preferredCampus;

    @Column(name = "AccountCode", nullable = true)
    private Long accountCode;

    public User() { } //Required by JPA

    public User(String sfu_id, long studentNumber, String name, String email, long phoneNumber, Role role, Campus campus, long accountCode) {
        this.sfuId = sfu_id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.preferredCampus = campus;
        this.accountCode = accountCode;
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

    public long getAccountCode() {
        //TODO: Not a good idea. Temp fix for rendering JSP's.
        return accountCode == null ? 0 : accountCode;
    }

    public void setAccountCode(long accountCode) {
        this.accountCode = accountCode;
    }
}