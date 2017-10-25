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
        sr.setUsername(rs.getString("User"));
        sr.setStartTime(rs.getTimestamp("Start"));
        sr.setEndTime(rs.getTimestamp("End"));
        sr.setCampus(Campus.valueOf(rs.getString("Campus").toUpperCase()));
        sr.setTitle(rs.getString("Title"));
        return sr;
    }
}
