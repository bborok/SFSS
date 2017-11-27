package com.zeta.Data.User;

import com.zeta.Models.Certificate;
import com.zeta.Models.Login;
import com.zeta.Models.Training;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao implements UserData {

    private JdbcTemplate jdbcTemplate;
    private Connection con;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            this.con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            String addUserSQL =
                    "INSERT INTO User (Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, " +
                            "StdNum, Role, CallSign, DriversLicenseLevel, DriversLicenseExpirationLevel" +
                            "isDeactivated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String addUserLanguageSQL = "insert into UserLanguage (User, Language) values (?, ?)";

            con.setAutoCommit(false);

            PreparedStatement addUser = con.prepareStatement(addUserSQL);
            addUser.setString(1, user.getUsername());
            addUser.setString(2, user.getName());
            addUser.setString(3, user.getEmail());
            addUser.setLong(4, user.getPhoneNumber());
            addUser.setInt(5, user.getAltPhoneNumber());
            addUser.setString(6, user.getPreferredCampus().toString());
            addUser.setLong(7, user.getStudentNumber());
            addUser.setString(8, user.getRole().toString());
            addUser.setString(9, user.getCallSign());
            addUser.setLong(10, user.getDriversLicenseLevel());
            addUser.setDate(11, (Date) user.getDriversLicenseExpirationDate());
            addUser.setBoolean(12, user.getIsDeactivated());

            addUser.execute();

            addUserLanguage(user, addUserLanguageSQL);

            con.commit();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void addUserLanguage(User user, String sql) throws SQLException {

        for (String language : user.getLanguages()) {

            PreparedStatement insertUserLanguage = con.prepareStatement(sql);
            insertUserLanguage.setString(1, user.getUsername());
            insertUserLanguage.setString(2, language);

            insertUserLanguage.execute();
        }
    }

    private void clearUserLanguage(User user) throws SQLException {

        String sql = "delete from UserLanguage where User = ?";

        PreparedStatement removeLanguage = con.prepareStatement(sql);
        removeLanguage.setString(1, user.getUsername());

        removeLanguage.execute();
    }

    @Override
    public boolean updateUser(User user) {
        try {
            String updateUserSQL = "UPDATE User SET Name = ?, Email = ?, PhoneNumber = ?, AltPhoneNumber = ?, " +
                    "PreferredCampus = ?, StdNum = ?, Role = ?, CallSign = ?, DriversLicenseLevel = ?, " +
                    "DriversLicenseExpirationDate = ? WHERE Username = ?";

            String addUserLanguageSQL = "insert into UserLanguage (User, Language) values (?, ?)";

            PreparedStatement updateUser = con.prepareStatement(updateUserSQL);
            updateUser.setString(1, user.getName());
            updateUser.setString(2, user.getEmail());
            updateUser.setLong(3, user.getPhoneNumber());
            updateUser.setInt(4, user.getAltPhoneNumber());
            updateUser.setString(5, user.getPreferredCampus().toString());
            updateUser.setLong(6, user.getStudentNumber());
            updateUser.setString(7, user.getRole().toString());
            updateUser.setString(8, user.getCallSign());
            updateUser.setInt(9, user.getDriversLicenseLevel());
            updateUser.setDate(10, (Date) user.getDriversLicenseExpirationDate());
            updateUser.setString(11, user.getUsername());

            updateUser.execute();

            clearUserLanguage(user);

            addUserLanguage(user, addUserLanguageSQL);

            con.commit();

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
        return users;
    }

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
            user = jdbcTemplate.queryForObject(userSQL, new Object[]{username, username}, new UserRowMapper());

            user.setTraining(getUserTraining(user));

            user.setLanguages(getUserLanguages(user));

        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public List<Certificate> getUserCertificates(User user) {
        try {
            String sql = "select CertificateName, Level, Number, ExpirationDate from UserCertificate where User = ?";
            return jdbcTemplate.query(sql, new Object[] {user.getUsername()}, new UserCertificateRowMapper());

        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<String> getUserLanguages(User user) {
        try {
            String sql = "select Language from UserLanguage where User = ?";
            return jdbcTemplate.query(sql, new Object[]{user.getUsername()}, new UserLanguageRowMapper());

        } catch (Exception e) {
            return null;
        }
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
            list = jdbcTemplate.query(sql, new Object[]{user.getUsername()}, new UserTrainingRowMapper());

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