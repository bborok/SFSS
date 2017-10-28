package com.zeta.Models;

// Login class gets username from user's input and passes it along to database to check if that user is allowed in.
public class Login {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}