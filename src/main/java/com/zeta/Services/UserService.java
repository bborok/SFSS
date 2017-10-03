package com.zeta.Services;

import com.zeta.Models.User;
import com.zeta.Repositories.UserDAO;
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
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserFromSFUId(String sfuid){
        return userDAO.get(sfuid);
    }

    public void addUser(User user){
        userDAO.add(user);
    }

    public List<User> getListOfUsers(){
        return userDAO.list();
    }
}
