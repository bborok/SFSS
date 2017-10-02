package com.zeta.Services;

import com.zeta.Models.User;
import com.zeta.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Allows user to interact with the database via a Repository.
 * UserController could access UserRepository directly, but the implementation
 * of this UserService may change if the say the application uses
 * SQL Queries instead of HibernateJPA.
 */
@Service
public class UserService {
    private UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserFromSFUId(String sfuid){
        return repository.findBySfuId(sfuid);
    }

    public User getUserFromStudentNumber(long studentNumber){
        return repository.findOne(studentNumber);
    }

    public User getUserFromEmail(String email){
        return repository.findByEmail(email);
    }

    public void addUser(User user){
        repository.save(user);
    }

}
