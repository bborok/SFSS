package com.zeta.Data;

import com.zeta.Models.Login;
import com.zeta.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserDao implements UserInterface{
    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Boolean addUser(User user) {
        try {
             String sql =
                        "INSERT INTO User (Username, Name, Email, PhoneNumber, PreferredCampus, " +
                                "StdNum, Role, CallSign, Training) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

             jdbcTemplate.update(sql, user.getUsername(), user.getName(), user.getEmail(),
                        (int) user.getPhoneNumber(), user.getPreferredCampus().toString(),
                        (int) user.getStudentNumber(), user.getRole().toString(),
                        user.getCallSign(), user.getTraining());
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateUser(User user) {
       try {
               String sql = "UPDATE User SET Name = ?, Email = ?, PhoneNumber = ?, PreferredCampus = ? , StdNum = ?, " +
                       "Role = ?, CallSign = ?, Training = ? WHERE Username = ?";

               jdbcTemplate.update(sql, user.getName(), user.getEmail(),
                       user.getPhoneNumber(), user.getPreferredCampus(), user.getStudentNumber(),
                       user.getRole(), user.getUsername(), user.getCallSign(), user.getTraining());
       } catch (Exception e) {
           return false;
       }
       return true;
    }

    @Override
    public Boolean removeUser(String username) {
        try {
                String sql = "UPDATE User SET isDeactivated = 1 WHERE Username = ?";
                jdbcTemplate.update(sql, username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public User getUser(String username) {
        User user;
        try {
                String sql = "select Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                        "CallSign, Training from User where Username = ? and " +
                        "   (select 1 from User where Username = ?) and isDeactivated = 0";

                user = jdbcTemplate.queryForObject(sql, new Object[] {username, username}, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserByLogin(Login login) {
        return getUser(login.getUsername());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try {
            String sql = "select Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, Training from User where isDeactivated = 0";

            users = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return users;
    }

    @Override
    public List<User> getDeactivatedUsers() {
        List<User> users;
        try {
            String sql = "select Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, Training from User where isDeactivated = 1";

            users = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return users;    }
}
// Code from UserDaoImpl, will be removed later on
/*
public class UserDAOImpl implements UserDAO1 {
    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User u) {

        // insert
        String sql = "INSERT INTO User (Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, u.getUsername(), u.getName(),
                u.getEmail(), u.getPhoneNumber(), u.getPreferredCampus().toString(),
                u.getStudentNumber(), u.getRole().toString());

    }

    @Override
    public void update(User user) {
        String sql = "UPDATE User SET Name = ?, Email = ?, PhoneNumber = ?, PreferredCampus = ? , StdNum = ?, " +
                "Role = ?, CallSign = ?, Training = ? WHERE Username = ?";

        jdbcTemplate.update(sql, user.getName(), user.getEmail(),
                user.getPhoneNumber(), user.getPreferredCampus(), user.getStudentNumber(),
                user.getRole(), user.getUsername(), user.getCallSign(), user.getTraining());
    }


    @Override
    public void delete(String username) {
        // implementation details goes here...
    }

    /**
     * Returns a user based on the sfuID
     *
     * @param username sfuID of User
     * @return User whose details match username
     */
    /*@Override
    public User get(String username) {
        // implementation details goes here...
        String sql = "SELECT * FROM User WHERE Username=\'" + username + "\'";
        return jdbcTemplate.query(sql, new UserResultSetExtractor());
    }

    @Override
    public User get(Login login) {
        return get(login.getUsername());
    }

    @Override
    public List<User> list() {
        String sql = "SELECT * FROM User";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
        return users;
    }
}
*/