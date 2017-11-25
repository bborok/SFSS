package com.zeta.Models;

import java.util.List;

/*
 * com.zeta.Models.User class
 */
public class User {

    private String username;
    private String name;
    private String email;
    private Long phoneNumber;
    private Long altPhoneNumber;
    private Campus preferredCampus;
    private Long studentNumber;
    private Role role;
    private String callSign;
    private List<Training> training;
    private Boolean isDeactivated;

    //TODO: Uncomment and implement this
    //private List<User> contacts = new ArrayList<User>();



//    @Column(name = "AccountCode", nullable = true)
//    private Long accountCode;

    public User() {
    } //Required by JPA

    public User(String username, Long studentNumber, String name, String email, Long phoneNumber, Long altPhoneNumber,
                Role role, Campus preferredCampus, String callSign, List<Training> training, Boolean isDeactivated) {
        this.username = username;
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.altPhoneNumber = altPhoneNumber;
        this.role = role;
        this.preferredCampus = preferredCampus;
        this.callSign = callSign;
        this.training = training;
        this.isDeactivated = isDeactivated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
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

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public List<Training> getTraining() {
        return training;
    }

    public void setTraining(List<Training> training) {
        this.training = training;
    }

    public void setIsDeactivated(Boolean isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

    public Boolean getIsDeactivated() {
        return isDeactivated;
    }

    public Long getAltPhoneNumber() { return altPhoneNumber; }

    public void setAltPhoneNumber(Long altPhoneNumber) { this.altPhoneNumber = altPhoneNumber; }
}