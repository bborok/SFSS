package com.zeta.Data.Shift;

import com.zeta.Models.ConfirmationStatus;
import com.zeta.Models.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Concrete implementation of the ShiftInterface class.
 */
@Repository
public class ShiftDao implements ShiftData {
    private JdbcTemplate jdbcTemplate;
    private Connection con;

    @Autowired
    public ShiftDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            this.con = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    /**
     * Returns a list of Shift objects.
     *
     * @return List<Shift>
     */
    @Override
    public List<Shift> getShifts() {
        try {
            String sql = "SELECT * FROM Shift ORDER BY Date DESC";
            return jdbcTemplate.query(sql, new ShiftRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Shift> getShiftsByUser(String username) {
        try {
            String sql = "SELECT * FROM Shift WHERE User = ? ORDER BY Date DESC";
            return jdbcTemplate.query(sql, new Object[]{username}, new ShiftRowMapper());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateAvailability(long id, ConfirmationStatus confirmationStatus) {
        String sql = "UPDATE  Shift SET Confirmed = ? WHERE ID = ?";
        try {
            jdbcTemplate.update(sql, confirmationStatus.toString(), id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Shift> getShiftsWithUsername(String username) {
        String sql = "SELECT * FROM Shift WHERE User = ?";
        return jdbcTemplate.query(sql, new ShiftRowMapper(), username);
    }

    @Override
    public List<Shift> getShiftsInTimeFrame(Date start, Date end) {
        String sql = "SELECT * FROM Shift WHERE Date >= ? AND Date <= ?";
        return jdbcTemplate.query(sql, new ShiftRowMapper(), start, end);
    }

    @Override
    public List<Shift> getShiftsWithSubmittedTimeCards() {
        try {
            String sql = "SELECT * FROM Shift WHERE isTimeCardSubmitted = 1 ORDER BY Date DESC";
            return jdbcTemplate.query(sql, new ShiftRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Shift getShift(long id) {
        try {
            String sql = "SELECT * FROM Shift WHERE ID=?";
            return jdbcTemplate.queryForObject(sql, new ShiftRowMapper(), id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Adds a new row to the Shift table.
     *
     * @param shift Shift object to save
     * @return True if successful, false otherwise
     */
    @Override
    public boolean saveShift(Shift shift) {
        try {
            String sql = "INSERT INTO Shift(Name, StartTime, EndTime, User, Campus, Location, Notes, Date, RequiredTraining, isTimeCardSubmitted) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    shift.getTitle(),
                    shift.getStart(),
                    shift.getEnd(),
                    shift.getUsername(),
                    shift.getCampus().toString(),
                    shift.getLocation(),
                    shift.getNotes(),
                    shift.getDate(),
                    shift.getRequiredTraining(),
                    shift.isTimeCardSubmitted()
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a row from the Shift table
     *
     * @param id id of Shift to delete
     * @return True if successful, false other wise
     */
    @Override
    public boolean deleteShift(long id) {

        try {
            con.setAutoCommit(false);

            //Delete all references in the UserTask table...
            String userTaskSql = "DELETE FROM UserTask WHERE Shift = ?";
            PreparedStatement deleteUserTask = con.prepareStatement(userTaskSql);
            deleteUserTask.setLong(1, id);
            deleteUserTask.execute();

            //Before deleting from the Shift table
            String shiftSql = "DELETE FROM Shift WHERE ID=?";
            PreparedStatement deleteShift = con.prepareStatement(shiftSql);
            deleteShift.setLong(1, id);
            deleteShift.execute();

            con.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting shift.");
            return false;
        }
    }
}
