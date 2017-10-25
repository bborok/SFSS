package com.zeta.Data;

import com.zeta.Models.Campus;
import com.zeta.Models.ShiftRaw;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftRawRowMapper implements RowMapper<ShiftRaw> {
    @Override
    public ShiftRaw mapRow(ResultSet rs, int i) throws SQLException {
        ShiftRaw sr = new ShiftRaw();
        sr.setId(rs.getLong("ID"));
        sr.setUsername(rs.getString("User"));
        sr.setStart(rs.getTimestamp("Start"));
        sr.setEnd(rs.getTimestamp("End"));
        sr.setCampus(Campus.valueOf(rs.getString("Campus").toUpperCase()));
        sr.setTitle(rs.getString("Title"));
        return sr;
    }
}
