package com.zeta.Data.Announcements;

import com.zeta.Models.Announcement;
import com.zeta.Models.Campus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AnnouncementsRowMapper implements RowMapper<Announcement> {

    @Override
    public Announcement mapRow(ResultSet rs, int i) throws SQLException {
        Announcement a = new Announcement();

//        a.setId(rs.getInt("ID"));
        a.setUsername(rs.getString("User"));

        a.setTitle(rs.getString("Title"));

        a.setMessage(rs.getString("Message"));

        a.setDate(rs.getDate("Date"));
        a.setCampus(Campus.valueOf(rs.getString("Campus").toUpperCase()));

        return a;
    }
}
