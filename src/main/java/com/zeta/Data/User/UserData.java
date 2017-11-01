package com.zeta.Data.User;

import com.zeta.Models.Login;
import com.zeta.Models.User;

import java.util.List;

// Every method will return false/null if query fails.
public interface UserData {

    // Set argument to null if no data
    public Boolean addUser(User user);

    // Set argument to null if no data
    public Boolean updateUser(User user);

    public Boolean removeUser(String username);

    public Boolean activateDeactivatedUser(String username);

    public List<User> getDeactivatedUsers();

    public User getUserByLogin(Login login);

    // Returns user if username exists
    public User getUser(String Username);

    // Returns list of all active users, ones that have access to program
    public List<User> getAllUsers();

    public Boolean getUserTraining(User user);

    // Date must be in the format YYYY-MM-DD
    public Boolean setUserTraining(String username, String training, String date);

    // Date must be in the format YYYY-MM-DD
    public Boolean updateUserTraining(String username, String training, String date);

    public Boolean removeTraining(String username, String training, String date);


}