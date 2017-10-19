package com.zeta.Data;

import com.zeta.Models.Login;
import com.zeta.Models.User;

import java.util.List;

public interface UserDAO1 {
    public void add(User user);

    public void update(User user);

    public void delete(String username);

    public User get(String sfuID);

    public User get(Login login);

    public List<User> list();
}
