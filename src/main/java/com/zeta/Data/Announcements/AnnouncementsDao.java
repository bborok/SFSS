package com.zeta.Data.Announcements;

import com.zeta.Models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

@Repository
public class AnnouncementsDao implements AnnouncementsData {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnnouncementsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addAnnouncement(Announcement announcement) {
        try {
            String sql =
                    "INSERT INTO Announcement (User, Title, Message, Date, Campus) VALUES (?, ?, ?, ?, ?)";

            jdbcTemplate.update(sql, announcement.getUsername(), announcement.getTitle(),
                    announcement.getMessage(), announcement.getDate());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Announcement showAnnouncements(int ID) {
        Announcement announcement = null;
        try {
            String sql = "SELECT * FROM Announcement WHERE ID = ?";

            announcement = jdbcTemplate.queryForObject(sql, new Object[]{ID}, new AnnouncementsRowMapper());

            return announcement;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Announcement> showAllAnnouncements() {
        List<Announcement> list;
        try {
            String sql = "SELECT * FROM Announcement";
            list = jdbcTemplate.query(sql, new AnnouncementsRowMapper());
        } catch (Exception e) {

            return null;
        }
        return list;
    }
}