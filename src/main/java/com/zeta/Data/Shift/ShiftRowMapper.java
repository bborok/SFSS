package com.zeta.Data.Shift;

import com.zeta.Models.Campus;
import com.zeta.Models.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ShiftRowMapper implements RowMapper<Shift> {
    @Override
    public Shift mapRow(ResultSet rs, int i) throws SQLException {
        Shift sr = new Shift();
        sr.setId(rs.getLong("ID"));
        sr.setTitle(rs.getString("Name"));
        sr.setDate((Date) rs.getDate("Date"));
        sr.setStart((Date) rs.getTimestamp("StartTime"));
        sr.setEnd((Date) rs.getTimestamp("EndTime"));
        sr.setCampus(Campus.valueOf(rs.getString("Campus").toUpperCase()));
        sr.setUsername(rs.getString("User"));
        sr.setLocation(rs.getString("Location"));
        sr.setNotes(rs.getString("Notes"));
        sr.setRequiredTraining(rs.getString("RequiredTraining"));
        sr.setTimeCardSubmitted(rs.getBoolean("isTimeCardSubmitted"));
        return sr;
    }
}
