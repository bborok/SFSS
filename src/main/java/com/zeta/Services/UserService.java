package com.zeta.Services;

import com.zeta.Data.UserInterface;
import com.zeta.Models.Login;
import com.zeta.Models.User;
import com.zeta.Data.UserDAO1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Allows user to interact with the database via a Repository.
 * UserController could access userDao directly, but the implementation
 * of this UserService may change if the say the application uses
 * SQL Queries instead of HibernateJPA.
 */
@Service
public class UserService {
    private UserInterface userInterface;

    @Autowired
    public UserService(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public User getUserFromUsername(String username){
        return userInterface.getUser(username);
    }

    public User getUserFromLogin(Login login) { return userInterface.getUserByLogin(login); }

    public void addUser(User user){
        userInterface.addUser(user);
    }

    public List<User> getListOfAllUsers(){
        return userInterface.getAllUsers();
    }
}