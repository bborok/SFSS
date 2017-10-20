package com.zeta.Repositories;

import com.zeta.Models.Login;
import com.zeta.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String sql = "INSERT INTO User (Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, u.getUsername(), u.getName(),
                u.getEmail(), u.getPhoneNumber(), u.getPreferredCampus().toString(),
                u.getStudentNumber(), u.getRole().toString());

    }

    @Override
    public void update(User user) {
        String sql = "UPDATE User SET Name=?, Email=?, PhoneNumber=?, "
                + "PreferredCampus=? , StdNum=?, Role=? WHERE Username=?";

        jdbcTemplate.update(sql, user.getName(), user.getEmail(),
                user.getPhoneNumber(), user.getPreferredCampus(), user.getStudentNumber(),
                user.getRole(), user.getUsername());

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
    @Override
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
