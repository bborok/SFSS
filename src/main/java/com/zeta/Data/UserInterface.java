package com.zeta.Data;

import com.zeta.Models.Login;
import com.zeta.Models.User;

import java.util.List;

public interface UserInterface {

    // Every method will return false/null if query fails.

    // Set argument to null if no data
    public Boolean addUser(User user);

    // Set argument to null if no data
    public Boolean updateUser(User user);

    public Boolean removeUser(String username);

    public Boolean activateDeactivatedUser(String username);

    public List<User> getDeactivatedUsers();
    // Returns user if username exists

    // Use this method for login
    public User getUser(String Username);

    public User getUserByLogin(Login login);

    // Returns list of all active users, ones that have access to program
    public List<User> getAllUsers();

    public Boolean getUserTraining(User user, String username);
}