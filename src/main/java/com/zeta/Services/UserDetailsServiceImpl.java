package com.zeta.Services;

import com.zeta.Data.UserInterface;
import com.zeta.Models.MyUserDetails;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserData userData;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userData.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return new MyUserDetails(user);
    }
}
