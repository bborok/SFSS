package com.zeta.Repositories;

import com.zeta.Models.User;

import java.util.List;

public interface UserDAO {
    public void add(User user);

    public void update(User user);

    public void delete(String username);

    public User get(String sfuID);

    public List<User> list();
}
