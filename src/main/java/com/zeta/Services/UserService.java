package com.zeta.Services;

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
    private UserDAO1 userDAO1;

    @Autowired
    public UserService(UserDAO1 userDAO1) {
        this.userDAO1 = userDAO1;
    }

    public User getUserFromUsername(String sfuid){
        return userDAO1.get(sfuid);
    }

    public User getUserFromLogin(Login login) { return userDAO1.get(login); }

    public void addUser(User user){
        userDAO1.add(user);
    }

    public List<User> getListOfUsers(){
        return userDAO1.list();
    }
}
