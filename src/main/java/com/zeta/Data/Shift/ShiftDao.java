package com.zeta.Data.Shift;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Data.User.UserDao;
import com.zeta.Data.User.UserData;
import com.zeta.Models.ShiftRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Concrete implementation of the ShiftInterface class.
 */
@Repository
public class ShiftDao implements ShiftData {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftDao(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
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

    @Override
    public ShiftRaw getShiftRaw(long id) {
        try {
            String sql = "SELECT * FROM Shift WHERE ID=?";
            ShiftRaw shiftRaw = jdbcTemplate.queryForObject(sql, new ShiftRawRowMapper(), id);
            return shiftRaw;
        } catch (Exception e){
            return null;
        }

    }

    /**
     * Adds a new row to the Shift table.
     * @param shiftRaw ShiftRaw object to save
     * @return True if successful, false otherwise
     */
    @Override
    public boolean saveShiftRaw(ShiftRaw shiftRaw) {
        try {
            String sql = "INSERT INTO Shift(Name, StartTime, EndTime, User, Campus, Location, Notes, Date, RequiredTraining) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    shiftRaw.getTitle(),
                    shiftRaw.getStart(),
                    shiftRaw.getEnd(),
                    shiftRaw.getUsername(),
                    shiftRaw.getCampus().toString(),
                    shiftRaw.getLocation(),
                    shiftRaw.getNotes(),
                    shiftRaw.getDate(),
                    shiftRaw.getRequiredTraining());
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
