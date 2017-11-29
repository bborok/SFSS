package com.zeta.Data.User;

import com.zeta.Models.Certificate;
import com.zeta.Models.Training;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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
        boolean activeUser = true;  // User is active when created
        try {
            String addUserSQL =
                    "INSERT INTO User (Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, " +
                            "StdNum, Role, CallSign, DriversLicenseLevel, DriversLicenseExpirationLevel" +
                            "isDeactivated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            addUser.setBoolean(12, activeUser);

            addUser.execute();

            addUserLanguage(user);

            con.commit();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private void addUserLanguage(User user) throws SQLException {

        String addUserLanguageSQL = "insert into UserLanguage (User, Language) values (?, ?)";

        for (String language : user.getLanguages()) {

            PreparedStatement insertUserLanguage = con.prepareStatement(addUserLanguageSQL);
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

            // Update user's languages
            clearUserLanguage(user);
            addUserLanguage(user);

            con.commit();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeUser(String username) {
        try {
            String sql = "UPDATE User SET isDeactivated = 1 WHERE Username = ?";
            jdbcTemplate.update(sql, username);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateDeactivatedUser(String username) {
        try {
            String sql = "UPDATE User SET isDeactivated = 0 WHERE Username = ?";
            jdbcTemplate.update(sql, username);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getDeactivatedUsers() {
        try {
            String sql = "select Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, TotalVolunteerHours, DriversLicenseLevel, DriversLicenseExpirationDate, isDeactivated " +
                    "from User where isDeactivated = 1";

            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        try {
            String userSQL = "select Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, StdNum, " +
                    "Role, CallSign, DriversLicenseLevel, DriversLicenseExpirationDate, TotalVolunteerHours, " +
                    "ParkingMinutes, isDeactivated from User where Username = ? and " +
                    "(select 1 from User where Username = ?) and isDeactivated = 0";
            User user = jdbcTemplate.queryForObject(userSQL, new Object[]{username, username}, new UserRowMapper());

            user.setTraining(getUserTraining(user));

            user.setLanguages(getUserLanguages(user));

            user.setCertificates(getUserCertificates(user));

            return user;
        } catch (Exception e) {
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
        try {
            String sql = "select Username, Name, Email, PhoneNumber, AltPhoneNumber, PreferredCampus, StdNum, Role, " +
                    "CallSign, DriversLicenseLevel, DriversLicenseExpirationDate, TotalVolunteerHours, , ParkingMinutes, " +
                    "isDeactivated from User where isDeactivated = 0";

            return jdbcTemplate.query(sql, new UserRowMapper());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Training> getUserTraining(User user) {
        try {
            String sql = "select Training, Hours, Date from UserTraining where User = ?";
            return jdbcTemplate.query(sql, new Object[]{user.getUsername()}, new UserTrainingRowMapper());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addUserTraining(String username, String training, String date, int hours) {
        try {
            String sql = "insert into UserTraining (User, Training, Date, Hours) values (?, ?, ?, ?)";
            jdbcTemplate.update(sql, username, training, date, hours);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUserTraining(String username, String training, String date, int hours) {
        try {
            String sql = "update UserTraining set Date = ?, Hours = ? where User = ? and Training = ?";
            jdbcTemplate.update(sql, date, hours, username, training);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeTraining(String username, String training) {
        try {
            String sql = "delete from UserTraining where User = ?, Training = ?";
            jdbcTemplate.update(sql, username, training);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateVolunteerHours(String username, int hours) {
        try {
            String sql = "update User set VolunteerHours = ? where User = ?";
            jdbcTemplate.update(sql, hours, username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getParkingMinutes(String username) throws SQLException{
        String sql = "Select ParkingMinutes from User where Username = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{username},
                (resultSet, i) -> resultSet.getInt("ParkingMinutes"));
    }

    @Override
    public boolean updateParkingMinutes(String username, int updatedMinutes) {
        try {
            String sql = "update User set ParkingMinutes = ? where Username = ?";

            jdbcTemplate.update(sql, updatedMinutes, username);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Certificate> getUserCertificates(User user) {
        try {
            String sql = "select CertificateName, Level, Number, ExpirationDate from UserCertificate where User = ?";
            return jdbcTemplate.query(sql, new Object[]{user.getUsername()}, new UserCertificateRowMapper());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addUserCertificate(User user, Certificate certificate) {
        try {
            String sql = "insert into UserCertificate (User, CertificateName, Level, Number, ExpirationDate) " +
                    "values (?, ?, ?, ?)";

            jdbcTemplate.update(sql, user.getUsername(), certificate.getName(), certificate.getLevel(),
                    certificate.getNumber(), certificate.getExpirationDate());

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUserCertificates(User user) {
        try {
            con.setAutoCommit(false);

            clearCertificates(user);

            addUserCertificates(user);

            con.commit();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // To be used in a PreparedStatement
    private void clearCertificates(User user) throws SQLException {

        String sql = "delete from UserCertificate where User = ?";

        PreparedStatement removeCertificates = con.prepareStatement(sql);
        removeCertificates.setString(1, user.getUsername());

        removeCertificates.execute();
    }

    // To be used in PreparedStatement
    private void addUserCertificates(User user) throws SQLException {
        String sql = "insert into UserCertificate(User, CertificateName, Level, Number, ExpirationDate) values " +
                "(?, ?, ?, ?, ?)";

        for (Certificate certificate : user.getCertificates()) {

            PreparedStatement addCertificate = con.prepareStatement(sql);
            addCertificate.setString(1, user.getUsername());
            addCertificate.setString(2, certificate.getName());
            addCertificate.setString(3, certificate.getLevel());
            addCertificate.setInt(4, certificate.getNumber());
            addCertificate.setDate(5, (Date) certificate.getExpirationDate());

            addCertificate.execute();
        }
    }

    @Override
    public boolean removeUserCertificate(User user, String certificateName) {
        try {
            String sql = "delete from UserCertificate where User = ? and CertificateName = ?";

            jdbcTemplate.update(sql, user.getUsername(), certificateName);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}