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
    public boolean saveTimeCard(TimeCard timeCard) {
        int isSubmitted = 0;    // saving timecard sets submitted to false
        return addTimeCard(timeCard, isSubmitted);
    }

    @Override
    public boolean submitTimeCard(TimeCard timeCard) {
        int isSubmitted = 1;    // submitting timecard sets submitted to true
        return addTimeCard(timeCard, isSubmitted);
    }


    @Override
    public boolean updateTimeCard(TimeCard timeCard) {
        int isSubmitted = 1; // only able to update timecard after it has been submitted
        try {

            String shiftSQL = "update Shift set Location = ?, Notes = ?, isTimeCardSubmitted = ? where User = ? and ID = ?";
            String userTaskSQL = "insert into UserTask (User, Shift, Task, Count) values (?, ?, ?, ?)";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL);
            updateShift.setString(1, timeCard.getLocation());
            updateShift.setString(2, timeCard.getNotes());
            updateShift.setInt(3, isSubmitted);
            updateShift.setString(4, timeCard.getUsername());
            updateShift.setLong(5, timeCard.getShiftId());

            updateShift.execute();

            // Remove data for that specific user and shift
            clearRecords(timeCard.getUsername(), timeCard.getShiftId());

            insertTasksIntoUserTask(timeCard, userTaskSQL);

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
            String shiftSQL = "select Campus, Location, Notes, isTimeCardSubmitted from Shift where User = ? and ID = ?";
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

    private boolean addTimeCard(TimeCard timeCard, int isSubmitted) {
        try {
            String shiftSQL = "update Shift set Campus = ?, Location = ?, Notes = ?, isTimeCardSubmitted = ? " +
                    "where User = ? and ID = ?";
            String userTaskSQL = "insert into UserTask (User, Shift, Task, Count) values (?, ?, ?, ?)";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL);
            updateShift.setString(1, timeCard.getCampus().toString());
            updateShift.setString(2, timeCard.getLocation());
            updateShift.setString(3, timeCard.getNotes());
            updateShift.setInt(4, isSubmitted);
            updateShift.setString(5, timeCard.getUsername());
            updateShift.setLong(6, timeCard.getShiftId());

            updateShift.execute();

            insertTasksIntoUserTask(timeCard, userTaskSQL);

            con.commit();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void clearRecords(String username, long shiftId) throws SQLException {

        String sql = "delete from UserTask where User = ? and Shift = ?";

        PreparedStatement removeRecords = con.prepareStatement(sql);
        removeRecords.setString(1, username);
        removeRecords.setLong(2, shiftId);

        removeRecords.execute();
    }

    // For each task update count and set if count is not 0
    private void insertTasksIntoUserTask(TimeCard timeCard, String sql) throws SQLException {

        for (Task task : timeCard.getTasks()) {
            if (task.getCount() == 0) {
                continue;
            }

            PreparedStatement insertUserTask = con.prepareStatement(sql);
            insertUserTask.setString(1, timeCard.getUsername());
            insertUserTask.setLong(2, timeCard.getShiftId());
            insertUserTask.setString(3, task.getTaskName());
            insertUserTask.setInt(4, task.getCount());

            insertUserTask.execute();
        }
    }
}