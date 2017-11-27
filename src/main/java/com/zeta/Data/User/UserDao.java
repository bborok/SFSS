package com.zeta.Data.User;

import com.zeta.Models.Login;
import com.zeta.Models.Training;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao implements UserData {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addUser(User user) {
        try {
             String sql =
                        "INSERT INTO User (Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, " +
                                "StdNum, Role, CallSign, DriversLicenseLevel, DriversLicenseExpirationLevel" +
                                "isDeactivated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

             jdbcTemplate.update(sql, user.getUsername(), user.getName(), user.getEmail(),
                        (int) user.getPhoneNumber(), user.getAltPhoneNumber(), user.getPreferredCampus().toString(),
                        (int) user.getStudentNumber(), user.getRole().toString(), user.getCallSign(),
                     user.getDriversLicenseLevel(), user.getDriversLicenseExpirationDate(), user.getIsDeactivated());
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
       try {
               String sql = "UPDATE User SET Name = ?, Email = ?, PhoneNumber = ?, AltPhoneNumber = ?, PreferredCampus = ?," +
                       " StdNum = ?, Role = ?, CallSign = ?, DriversLicenseLevel = ?, DriversLicenseExpirationDate = ? " +
                       "WHERE Username = ?";

               jdbcTemplate.update(sql, user.getName(), user.getEmail(),
                       user.getPhoneNumber(), user.getAltPhoneNumber(), user.getPreferredCampus().toString(),
                       user.getStudentNumber(), user.getRole().toString(), user.getCallSign(),
                       user.getDriversLicenseLevel(), user.getDriversLicenseExpirationDate(), user.getUsername());
       } catch (Exception e) {
           return false;
       }
       return true;
    }

    @Override
    public boolean removeUser(String username) {
        try {
                String sql = "UPDATE User SET isDeactivated = 1 WHERE Username = ?";
                jdbcTemplate.update(sql, username);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean activateDeactivatedUser(String username) {
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
                String userSQL = "select Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, StdNum, " +
                        "Role, CallSign, DriversLicenseLevel, DriversLicenseExpirationDate, isDeactivated " +
                        "from User where Username = ? and (select 1 from User where Username = ?) and isDeactivated = 0";
                user = jdbcTemplate.queryForObject(userSQL, new Object[] {username, username}, new UserRowMapper());

                List<Training> trainings = getUserTraining(user);
                if (trainings != null) {
                    user.setTraining(trainings);
                } else {
                    user.setTraining(null);
                }

        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try {
            String sql = "select Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, DriversLicenseLevel, DriversLicenseExpirationDate, isDeactivated " +
                    "from User where isDeactivated = 0";

            users = jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
        return users;
    }

    @Override
    public List<Training> getUserTraining(User user) {
        List<Training> list;
        try {
                String sql = "select Training, Hours, Date from UserTraining where User = ?";
                list = jdbcTemplate.query(sql, new Object[] {user.getUsername()}, new UserTrainingRowMapper());

        } catch (Exception e) {
            return null;
        }
        return list;
    }

    @Override
    public boolean addUserTraining(String username, String training, String date, int hours) {
        try {
            String sql = "insert into UserTraining (User, Training, Date, Hours) values (?, ?, ?, ?)";
            jdbcTemplate.update(sql, username, training, date, hours);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserTraining(String username, String training, String date, int hours) {
        try {
            String sql = "update UserTraining set Date = ?, Hours = ? where User = ? and Training = ?";
            jdbcTemplate.update(sql, date, hours, username, training);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeTraining(String username, String training) {
        try {
            String sql = "delete from UserTraining where User = ?, Training = ?";
            jdbcTemplate.update(sql, username, training);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}