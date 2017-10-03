package com.zeta.Repositories;

import com.zeta.Models.Campus;
import com.zeta.Models.Role;
import com.zeta.Models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetExtractor implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {

        if (rs.next()) {
            User user = new User();
            user.setSfuId(rs.getString("SFU_ID"));
            user.setName(rs.getString("Name"));
            user.setEmail(rs.getString("Email"));
            user.setPhoneNumber(rs.getLong("PhoneNumber"));
            user.setPreferredCampus(Campus.valueOf(rs.getString("PreferredCampus")));
            user.setStudentNumber(rs.getLong("StdNum"));
            user.setRole(Role.valueOf(rs.getString("Role")));
            return user;
        }

        return null;
    }
}
