package com.zeta.Data;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;
import com.zeta.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShiftDao implements ShiftInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Shift> getShifts() {
        //TODO: Optimize this. Currently runs O(n^2)
        //Fetch shift's in Raw Table Format
        List<ShiftRaw> shiftRaws;
        String shiftRawQuery = "SELECT * FROM Shift";
        shiftRaws = jdbcTemplate.query(shiftRawQuery, new ShiftRawRowMapper());

        //Get all Users
        List<User> users;
        String userseQuery = "SELECT Username, Name, Email, PhoneNumber, PreferredCampus, StdNum, Role, " +
                "CallSign, isDeactivated FROM User WHERE isDeactivated = 0";
        users = jdbcTemplate.query(userseQuery, new UserRowMapper());

        //Join these two together
        //TODO: Alternate method. Query the User for each string username in ShiftRaw
        List<Shift> shifts = new ArrayList<>();
        for (ShiftRaw sr : shiftRaws)
            for (User u : users)
                if (sr.getUsername().equals(u.getUsername()))
                    shifts.add(new Shift(
                            sr.getStartTime(),
                            sr.getEndTime(),
                            sr.getTitle(),
                            u,
                            sr.getCampus()
                    ));

        return shifts;
    }
}
