package com.zeta.Data.TimeCard;

import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TimeCardDao implements TimeCardData {

    private JdbcTemplate jdbcTemplate;
    private Connection con;

    @Autowired
    public TimeCardDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            this.con = dataSource.getConnection();
        } catch (SQLException e) {
            // TODO: implement proper error handling
            //If this error throws then this means that the database can't be connected to at all
            System.out.println(e.getErrorCode());
        }
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
                        (String[]) new Object[]{
                        timeCard.getUsername(), timeCard.getShiftId(), task.getTaskName(), task.getCount()});

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
    public TimeCard getTimeCard(String username, long shiftId) {
        TimeCard timeCard = null;
        try {
            String shiftSQL = "select Campus, Location, Notes from Shift where User = ? and ID = ?";
            String taskSQL = "select Name from Task where isDeactivated = 0 order by Name asc";
            String userTaskSQL = "select Task, Count from UserTask where User = ? and Shift = ? order by Task asc";

            // Create time card and set campus, location, notes
            timeCard = jdbcTemplate.queryForObject(shiftSQL, new Object[]{username, shiftId}, new TimeCardRowMapper());

            // Get list of all tasks
            List<Task> allTasks = jdbcTemplate.query(taskSQL, new TaskMapper());

            // Get list of all tasks user did in particular shift
            List<Task> userTasks = jdbcTemplate.query(
                    userTaskSQL, new Object[]{username, shiftId}, new UserTaskMapper());

            // Populates all task counts with counts from user tasks
            for (Task task : allTasks) {
                for (Task userTask : userTasks) {
                    if (task.getTaskName().equals(userTask.getTaskName())) {
                        task.setCount(userTask.getCount());
                    }
                }
            }

            timeCard.setUsername(username);
            timeCard.setShiftId(shiftId);
            timeCard.setTasks(allTasks);

        } catch (Exception e) {
            return null;
        }
        return timeCard;
    }
}