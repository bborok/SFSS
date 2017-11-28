package com.zeta.Data.User;

import com.zeta.Models.Certificate;
import com.zeta.Models.Login;
import com.zeta.Models.Training;
import com.zeta.Models.User;

import java.util.List;

// Every method will return false/null if query fails.
public interface UserData {

    // Set argument to null if no data
    public boolean addUser(User user);

    // Set argument to null if no data
    public boolean updateUser(User user);

    public boolean removeUser(String username);

    public boolean activateDeactivatedUser(String username);

    public List<User> getDeactivatedUsers();

    // Returns user if username exists
    public User getUser(String Username);

    public List<String> getUserLanguages(User user);

    // Returns list of all active users, ones that have access to program
    public List<User> getAllUsers();

    public List<Training> getUserTraining(User user);

    // Date must be in the format YYYY-MM-DD
    public boolean addUserTraining(String username, String training, String date, int hours);

    // Date must be in the format YYYY-MM-DD
    // Updates date and hours for particular user and training
    public boolean updateUserTraining(String username, String training, String date, int hours);

    // Removes record of training for particular user and training
    public boolean removeTraining(String username, String training);

    public boolean updateVolunteerHours(String username, int hours);

    public List<Certificate> getUserCertificates(User user);

    public boolean addUserCertificate(User user, Certificate certificate);

    public boolean updateUserCertificates(User user);

    public boolean removeUserCertificate(User user, String CertificateName);
}