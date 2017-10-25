package com.zeta.Data.TimeCard;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import com.zeta.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TimeCardDao implements TimeCardData {

    private JdbcTemplate jdbcTemplate;
    private Connection con;

    public TimeCardDao() {
        PersistenceConfig config = new PersistenceConfig();

        this.jdbcTemplate = new JdbcTemplate(config.dataSource());
        this.con = config.getConnection();
    }

    @Override
    public Boolean addTimeCard(TimeCard timeCard) {
        try {
            String shiftSQL = "update Shift set Location = ?, Notes = ? where User = ?";
            String userTaskSQL = "insert into UserTask (User, Shift, Task, Count) values (?, ?, ?, ?)";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL,
                    (String[]) new Object[]{timeCard.getLocation(), timeCard.getNotes(), timeCard.getUsername()});

            updateShift.execute();

            for (Task task : timeCard.getTasks()) {
                PreparedStatement insertUserTask = con.prepareStatement(userTaskSQL,
                        (String[]) new Object[]{timeCard.getUsername(), timeCard.getShiftId(), task.getTaskName(), task.getCount()});

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
                    (String[]) new Object[]{timeCard.getLocation(), timeCard.getNotes(), timeCard.getUsername()});

            updateShift.execute();

            for (Task task : timeCard.getTasks()) {
                PreparedStatement updateUserTask = con.prepareStatement(userTaskSQL,
                        (String[]) new Object[]{
                        task.getTaskName(), task.getCount(), timeCard.getUsername(), timeCard.getShiftId()});

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