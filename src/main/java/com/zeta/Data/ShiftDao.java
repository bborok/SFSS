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

    @Override
    public Shift saveShift(ShiftRaw sR) {
        String sql = "INSERT INTO Shift(Title, Start, End, Username, Campus) " +
                "VALUES (?, ?, ?, ?, ?)";
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, sR.getTitle());
                statement.setTimestamp(2, sR.getStart());
                statement.setTimestamp(3, sR.getEnd());
                statement.setString(4, sR.getUsername());
                statement.setString(5, sR.getCampus().toString());
                return statement;
            }
        }, holder);
        Long pK = holder.getKey().longValue();
        User u = userInterface.getUser(sR.getUsername());
        return new Shift(pK, sR.getTitle(), sR.getStart(), sR.getEnd(), sR.getCampus(), u);
    }

    @Override
    public Shift getShift(long id) {
        String sql = "SELECT * FROM Shift WHERE ID=?";
        ShiftRaw sR = jdbcTemplate.queryForObject(sql, new ShiftRawRowMapper(), id);
        User u = userInterface.getUser(sR.getUsername());
        return new Shift(sR.getId(), sR.getTitle(), sR.getStart(), sR.getEnd(), sR.getCampus(), u);
    }

    @Override
    public Shift deleteShift(long id) {
        return null;
    }
}
