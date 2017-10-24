package com.zeta.Data;

import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import com.zeta.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class TimeCardDao implements TimeCardData {

    private JdbcTemplate jdbcTemplate;
    private Connection con;

    public TimeCardDao(DataSource dataSource, Connection con) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.con = con;
    }

    @Override
    public Boolean addTimeCard(TimeCard timeCard) {
        try {
            String shiftSQL = "update Shift set Location = ?, Notes = ? where User = ?";
            String userTaskSQL = "insert into UserTask (User, Shift, Task, Count) values (?, ?, ?, ?)";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL,
                    (String[]) new Object[]{timeCard.getLocation(), timeCard.getNotes(), timeCard.getUser()});

            updateShift.execute();

            for (Task task : timeCard.getTasks()) {
                PreparedStatement insertUserTask = con.prepareStatement(userTaskSQL,
                        (String[]) new Object[]{timeCard.getUser(), timeCard.getShiftId(), task.getTaskName(), task.getCount()});

                insertUserTask.execute();
            }

            con.commit();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateTimeCard(TimeCard timeCard) {
        try {

            String shiftSQL = "update Shift set Location = ?, Notes = ? where User = ?";
            String userTaskSQL = "update UserTask set Task = ?, Count = ? where User = ? and Shift = ?";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL,
                    (String[]) new Object[]{timeCard.getLocation(), timeCard.getNotes(), timeCard.getUser()});

            updateShift.execute();

            for (Task task : timeCard.getTasks()) {
                PreparedStatement updateUserTask = con.prepareStatement(userTaskSQL,
                        (String[]) new Object[]{
                        task.getTaskName(), task.getCount(), timeCard.getUser(), timeCard.getShiftId()});

                updateUserTask.execute();
            }

            con.commit();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public TimeCard getTimeCard(User username, long shiftId) {
        try {

        } catch (Exception e) {
            return null;
        }
        return null;
    }
}