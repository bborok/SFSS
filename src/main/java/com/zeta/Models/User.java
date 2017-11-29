package com.zeta.Models;

import java.util.Date;
import java.util.List;

/*
 * com.zeta.Models.User class
 */
public class User {

    private String username;
    private String name;
    private String email;
    private Long phoneNumber;
    private int altPhoneNumber;
    private Campus preferredCampus;
    private Long studentNumber;
    private Role role;
    private String callSign;
    private int driversLicenseLevel;
    private Date driversLicenseExpirationDate;
    private List<Training> training;
    private List<String> languages;
    private List<Certificate> certificates;
    private int volunteerMinutes;
    private int parkingMinutes;
    private Boolean isDeactivated;

    public User() {
    } //Required by JPA

    public User(String username, Long studentNumber, String name, String email, Long phoneNumber, int altPhoneNumber,
                Role role, Campus preferredCampus, String callSign, int driversLicenseLevel,
                Date driversLicenseExpirationDate, List<Training> training, List<String> languages,
                List<Certificate> certificates, int volunteerMinutes, int parkingMinutes, Boolean isDeactivated) {
        this.username = username;
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.altPhoneNumber = altPhoneNumber;
        this.role = role;
        this.preferredCampus = preferredCampus;
        this.callSign = callSign;
        this.volunteerMinutes = volunteerMinutes;
        this.driversLicenseLevel = driversLicenseLevel;
        this.driversLicenseExpirationDate = driversLicenseExpirationDate;
        this.training = training;
        this.languages = languages;
        this.certificates = certificates;
        this.parkingMinutes = parkingMinutes;
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

    public int getAltPhoneNumber() {
        return altPhoneNumber;
    }

    public void setAltPhoneNumber(int altPhoneNumber) {
        this.altPhoneNumber = altPhoneNumber;
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

    public int getDriversLicenseLevel() {
        return driversLicenseLevel;
    }

    public void setDriversLicenseLevel(int driversLicenseLevel) {
        this.driversLicenseLevel = driversLicenseLevel;
    }

    public Date getDriversLicenseExpirationDate() {
        return driversLicenseExpirationDate;
    }

    public void setDriversLicenseExpirationDate(Date driversLicenseExpirationDate) {
        this.driversLicenseExpirationDate = driversLicenseExpirationDate;
    }

    public List<Training> getTraining() {
        return training;
    }

    public void setTraining(List<Training> training) {
        this.training = training;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public int getVolunteerMinutes() { return volunteerMinutes; }

    public void setVolunteerMinutes(int volunteerMinutes) { this.volunteerMinutes = volunteerMinutes; }

    public int getParkingMinutes() {
        return parkingMinutes;
    }

    public void setParkingMinutes(int parkingMinutes) {
        this.parkingMinutes = parkingMinutes;
    }

    public void setIsDeactivated(Boolean isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

    public Boolean getIsDeactivated() {
        return isDeactivated;
    }
}