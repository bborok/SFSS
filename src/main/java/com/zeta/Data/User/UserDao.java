package com.zeta.Data.User;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Models.Login;
import com.zeta.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao implements UserData {
    private JdbcTemplate jdbcTemplate;

    public UserDao() {
        this.jdbcTemplate = new JdbcTemplate(new PersistenceConfig().dataSource());
    }

    @Override
    public Boolean addUser(User user) {
        try {
             String sql =
                        "INSERT INTO User (Username, Name, Email, PhoneNumber, PreferredCampus, " +
                                "StdNum, Role, CallSign) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

             jdbcTemplate.update(sql, user.getUsername(), user.getName(), user.getEmail(),
                        (int) user.getPhoneNumber(), user.getPreferredCampus().toString(),
                        (int) user.getStudentNumber(), user.getRole().toString(),
                        user.getCallSign());
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
                       "Role = ?, CallSign = ? WHERE Username = ?";

               jdbcTemplate.update(sql, user.getName(), user.getEmail(),
                       user.getPhoneNumber(), user.getPreferredCampus(), user.getStudentNumber(),
                       user.getRole(), user.getUsername(), user.getCallSign());
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
    public Boolean activateDeactivatedUser(String username) {
        try {
            String sql = "UPDATE User SET isDeactivated = 0 WHERE Username = ?";
            jdbcTemplate.update(sql, username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> getDeactivatedUsers() {
        List<User> users;
        try {
            String sql = "select Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, isDeactivated from User where isDeactivated = 1";

            users = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return users;    }

    @Override
    public User getUserByLogin(Login login) {
        return getUser(login.getUsername());
    }

    @Override
    public User getUser(String username) {
        User user;
        try {
                String userSQL = "select Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                        "CallSign, isDeactivated from User where Username = ? and " +
                        "(select 1 from User where Username = ?) and isDeactivated = 0";
                user = jdbcTemplate.queryForObject(userSQL, new Object[] {username, username}, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try {
            String sql = "select Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, isDeactivated from User where isDeactivated = 0";

            users = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return users;
    }

    @Override
    public Boolean getUserTraining(User user) {
        List<String> list;
        try {
                String sql = "select Training from UserTraining where User = ?";
                list = jdbcTemplate.queryForList(sql, new Object[] {user.getUsername()}, String.class);
                user.setTraining(list);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean setUserTraining(String username, String training, String date) {
        try {
            String sql = "insert into UserTraining (User, Training, Date) values (?, ?, ?)";
            jdbcTemplate.update(sql, username, training, date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateUserTraining(String username, String training, String date) {
        try {
            String sql = "update UserTraining set Training = ?, Date = ? where User = ?";
            jdbcTemplate.update(sql, training, date, username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean removeTraining(String username, String training, String date) {
        try {
            String sql = "delete from UserTraining where User = ?, Training = ?, Date = ? limit 1";
            jdbcTemplate.update(sql, username, training, date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}