package com.zeta.Data;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    @Override
    public List<Shift> getShifts() {
        //TODO: Optimize this. Currently runs O(n^2)
        //Fetch shift's in Raw Table Format
        List<ShiftRaw> shiftRaws;
        String shiftRawQuery = "SELECT * FROM Shift";
        shiftRaws = jdbcTemplate.query(shiftRawQuery, new ShiftRawRowMapper());

        //Get all Users
        List<User> users = userInterface.getAllUsers();

        //Join these two together
        //TODO: Alternate method. Query the User for each string username in ShiftRaw
        List<Shift> shifts = new ArrayList<>();
        for (ShiftRaw sr : shiftRaws)
            for (User u : users)
                if (sr.getUsername().equals(u.getUsername()))
                    shifts.add(new Shift(
                            sr.getTitle(),
                            sr.getStart(),
                            sr.getEnd(),
                            sr.getCampus(),
                            u
                    ));

        return shifts;
    }

    @Override
    public Shift saveShift(ShiftRaw shiftRaw) {
        String sql = "INSERT INTO Shift(Title, Start, End, User, Campus) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, shiftRaw.getTitle(), shiftRaw.getStart(),
                shiftRaw.getEnd(), shiftRaw.getUsername(), shiftRaw.getCampus());
        //TODO: GET ID OF ROW YOU Just Inserted
//        ShiftRaw querySr =
        User u = userInterface.getUser(shiftRaw.getUsername());
        return new Shift(shiftRaw.getTitle(), shiftRaw.getStart(), shiftRaw.getEnd(), shiftRaw.getCampus(), u);
    }

    @Override
    public Shift getShift(long id) {
        String sql = "SELECT * FROM Shift WHERE ID=?";
        ShiftRaw shiftRaw = jdbcTemplate.queryForObject(sql, new ShiftRawRowMapper(), id);
        User u = userInterface.getUser(shiftRaw.getUsername());
        return new Shift(shiftRaw.getTitle(), shiftRaw.getStart(), shiftRaw.getEnd(), shiftRaw.getCampus(), u);
    }

    @Override
    public Shift deleteShift(long id) {
        return null;
    }
}
