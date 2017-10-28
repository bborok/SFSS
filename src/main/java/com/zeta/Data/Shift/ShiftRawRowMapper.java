package com.zeta.Data.Shift;

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
        sr.setTitle(rs.getString("Name"));
        sr.setDate(rs.getDate("Date"));
        sr.setStart(rs.getTimestamp("StartTime"));
        sr.setEnd(rs.getTimestamp("EndTime"));
        sr.setCampus(Campus.valueOf(rs.getString("Campus").toUpperCase()));
        sr.setUsername(rs.getString("User"));
        sr.setLocation(rs.getString("Location"));
        sr.setNotes(rs.getString("Notes"));
        sr.setRequiredTraining(rs.getString("RequiredTraining"));
        return sr;
    }
}
