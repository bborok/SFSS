package com.zeta.Data.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLanguageRowMapper implements RowMapper<String>{
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString("Language");
    }
}
