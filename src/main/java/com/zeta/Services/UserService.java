package com.zeta.Services;

import com.zeta.Data.UserInterface;
import com.zeta.Models.Login;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Allows user to interact with the data layer.
 * UserController could access UserInterface directly.
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
        if (!userInterface.addUser(user)) {
            // TODO: Error handle this
        }
    }

    public List<User> getListOfAllUsers(){
        return userInterface.getAllUsers();
    }
}