package com.zeta.Models;

import javax.persistence.*;

/*
 * com.zeta.Models.User class
 */
@Entity
public class User {

    @Id
    private long studentNumber;

    private String name;

    private String email;

    private int phoneNumber;


    //TODO: Uncomment and implement this
//    private List<User> contacts = new ArrayList<User>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Campus campus;

    private int accountCode;

    public User() { } //Required by JPA

    // Base constructor
    public User(String name, String email, int phoneNumber, int studentNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
    }

    public User(int studentNumber, String name, String email, int phoneNumber, Role role, Campus campus, int accountCode) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.campus = campus;
        this.accountCode = accountCode;
    }

    public User(int studentNumber, String name, String email) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(int accountCode) {
        this.accountCode = accountCode;
    }
}