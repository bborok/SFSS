package com.zeta.Data.Announcements;

import com.zeta.Models.Announcements;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnouncementsRowMapper implements RowMapper<Announcements> {

    @Override
    public Announcements mapRow(ResultSet rs, int i) throws SQLException {
        Announcements a = new Announcements();
        a.setId(rs.getInt("ID"));
        a.setUsername(rs.getString("Username"));
        a.setTitle(rs.getString("Title"));
        a.setMessage(rs.getString("Message"));
        a.setDate(rs.getDate("Date"));
        return a;
    }
}
