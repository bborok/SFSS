package com.zeta.Data;

import com.zeta.Models.Login;
import com.zeta.Models.User;

import java.util.List;

public interface UserInterface {

    // ** Every method may throw exception, need to have a try-catch around each one **

    // Set argument to null if no data
    public void addUser(User user);

    // Set argument to null if no data
    public void updateUser(User user);

    public void removeUser(String username);

    // Returns user if username exists
    // Use this method for login
    public User getUser(String Username);

    public User getUserByLogin(Login login);

    public List<User> getUsers(/* TODO: insert arguments here*/);

    public List<User> getAllUsers();

    public List<User> getDeactivatedUsers();
}