package com.zeta.Data;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShiftDao implements ShiftInterface {
    private JdbcTemplate jdbcTemplate;
    private UserInterface userInterface;

    @Autowired
    public ShiftDao(DataSource dataSource, UserInterface userInterface) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userInterface = userInterface;
    }

    //Shift Methods

    /**
     * Gets a List of Shift objects from the Database
     * @return List<Shift>
     */
    @Override
    public List<Shift> getShifts() {
        //TODO: Optimize this. Currently runs O(n^2)
        //Fetch shift's in Raw Table Format
        List<ShiftRaw> shiftRaws;
        String shiftRawQuery = "SELECT * FROM Shift";
        shiftRaws = jdbcTemplate.query(shiftRawQuery, new ShiftRawRowMapper());

        //Get all Users
        List<User> users = userInterface.getAllUsers();

        //Join ShiftRaw's with Users to generate Shift's
        //TODO: Alternate method. Query the User for each string username in ShiftRaw
        List<Shift> shifts = new ArrayList<>();
        for (ShiftRaw sR : shiftRaws)
            for (User u : users)
                if (sR.getUsername().equals(u.getUsername()))
                    shifts.add(new Shift(
                            sR.getId(),
                            sR.getTitle(),
                            sR.getStart(),
                            sR.getEnd(),
                            sR.getCampus(),
                            u
                    ));

        return shifts;
    }

    /**
     * Gets a Shift object from the Shift table
     * @param id id of Shift to get
     * @return Shift object
     */
    @Override
    public Shift getShift(long id) {
        String sql = "SELECT * FROM Shift WHERE ID=?";
        ShiftRaw sR = jdbcTemplate.queryForObject(sql, new ShiftRawRowMapper(), id);
        User u = userInterface.getUser(sR.getUsername());
        return new Shift(sR.getId(), sR.getTitle(), sR.getStart(), sR.getEnd(), sR.getCampus(), u);
    }

    //ShiftRaw Methods

    /**
     * Returns a list of ShiftRaw objects.
     * @return List<ShiftRaw>
     */
    @Override
    public List<ShiftRaw> getShiftRaws() {
        String shiftRawQuery = "SELECT * FROM Shift";
        return jdbcTemplate.query(shiftRawQuery, new ShiftRawRowMapper());
    }

    /**
     * Adds a new row to the Shift table.
     * @param shiftRaw ShiftRaw object to save
     * @return True if successful, false otherwise
     */
    @Override
    public boolean saveShift(ShiftRaw shiftRaw) {
        try {
            String sql = "INSERT INTO Shift(Title, Start, End, Username, Campus) " +
                    "VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, shiftRaw.getTitle(), shiftRaw.getStart(), shiftRaw.getEnd(), shiftRaw.getUsername(), shiftRaw.getCampus().toString());
            return true;
        } catch (Exception e){
            return false;
        }

    }

    /**
     * Deletes a row from the Shift table
     * @param id id of Shift to delete
     * @return True if successful, false other wise
     */
    @Override
    public boolean deleteShift(long id) {
        try {
            String sql = "DELETE FROM Shift WHERE ID=?";
            jdbcTemplate.update(sql, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
