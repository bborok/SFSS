package com.zeta.Data.Announcements;

import com.zeta.Models.Announcements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AnnouncementsDao implements AnnouncementsData{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnnouncementsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Boolean addAnnouncement(Announcements announcements) {
        try {
            String sql =
                    "INSERT INTO Announcement (ID, User, Title, Message, Date) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, announcements.getUsername(), announcements.getTitle(),
                    announcements.getMessage(), announcements.getDate());
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public Announcements showAnnouncements (int id) {
        Announcements announcements;
        try {
            String sql =
                    "SELECT * FROM Announcement WHERE User = ?";
            announcements = jdbcTemplate.queryForObject(sql, new AnnouncementsRowMapper(), id);
            return announcements;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Announcements> showAllAnnouncements() {
        List<Announcements> list;
        try {
            String sql =
                    "SELECT * FROM Announcement";
            list = jdbcTemplate.query(sql, new AnnouncementsRowMapper());
        } catch (Exception e) {
            return null;
        } return list;
    }

}
