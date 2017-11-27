package com.zeta.Data.User;

import com.zeta.Models.Campus;
import com.zeta.Models.Role;
import com.zeta.Models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("Username"));
        user.setName(rs.getString("Name"));
        user.setEmail(rs.getString("Email"));
        user.setPhoneNumber(rs.getLong("PhoneNumber"));
        user.setAltPhoneNumber(rs.getInt("AltPhoneNumber"));

        if (rs.getString("PreferredCampus") == null){
            user.setPreferredCampus(null);
        } else{
            user.setPreferredCampus(Campus.valueOf(rs.getString("PreferredCampus").toUpperCase()));
        }

        user.setStudentNumber(rs.getLong("StdNum"));

        if (rs.getString("Role") == null){
            user.setRole(null);
        } else {
            user.setRole(Role.valueOf(rs.getString("Role").toUpperCase()));
        }

        user.setCallSign(rs.getString("CallSign"));
        user.setDriversLicenseLevel(rs.getInt("DriversLicenseLevel"));
        user.setDriversLicenseExpirationDate(rs.getDate("DriversLicenseExpirationDate"));
        user.setIsDeactivated(rs.getBoolean("isDeactivated"));

        return user;
    }
}
