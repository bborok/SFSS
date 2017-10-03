package com.zeta.Repositories;

import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User u) {

        // insert
        String sql = "INSERT INTO User (SFU_ID, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, u.getSfuId(), u.getName(),
                u.getEmail(), u.getPhoneNumber(), u.getPreferredCampus().toString(),
                u.getStudentNumber(), u.getRole().toString());

    }

    @Override
    public void update(User user) {
        String sql = "UPDATE User SET Name=?, Email=?, PhoneNumber=?, "
                + "PreferredCampus=? , StdNum=?, Role=? WHERE SFU_ID=?";

        jdbcTemplate.update(sql, user.getName(), user.getEmail(),
                user.getPhoneNumber(), user.getPreferredCampus(), user.getStudentNumber(),
                user.getRole(), user.getSfuId());

    }


    @Override
    public void delete(String sfuId) {
        // implementation details goes here...
    }

    /**
     * Returns a user based on the sfuID
     *
     * @param sfuId sfuID of User
     * @return User whose details match sfuId
     */
    @Override
    public User get(String sfuId) {
        // implementation details goes here...
        String sql = "SELECT * FROM User WHERE SFU_ID=\'" + sfuId + "\'";
        return jdbcTemplate.query(sql, new UserResultSetExtractor());
    }
}
