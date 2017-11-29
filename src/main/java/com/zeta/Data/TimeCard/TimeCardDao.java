package com.zeta.Data.TimeCard;

import com.zeta.Data.Task.TaskRowMapper;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
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
        // TimeCard is not being submitted by this method
        timeCard.setTimeCardSubmitted(false);
        return updateRecords(timeCard);
    }

    @Override
    public boolean submitTimeCard(TimeCard timeCard) {
        // TimeCard IS being submitted by this method
        timeCard.setTimeCardSubmitted(true);
        return updateRecords(timeCard);
    }

    private boolean updateRecords(TimeCard timeCard) {

        if (timeCardRecordExist(timeCard))
            return updateTimeCard(timeCard);

        return addNewTimeCard(timeCard);
    }

    // Returns true if user-shift combo record exists
    @Override
    public boolean timeCardRecordExist(TimeCard timeCard) {
        try {
            Integer result = jdbcTemplate.queryForObject(
                    "select exists(select ID from UserTask where User = ? and Shift = ?)",
                    new Object[]{timeCard.getUsername(), timeCard.getShiftId()}, Integer.class);

            return result != 0;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean addNewTimeCard(TimeCard timeCard) {
        try {
            String shiftSQL = "update Shift set Campus = ?, Location = ?, Notes = ?, isTimeCardSubmitted = ? " +
                    "where User = ? and ID = ?";
            String userTaskSQL = "insert into UserTask (User, Shift, Task, Count) values (?, ?, ?, ?)";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL);
            updateShift.setString(1, timeCard.getCampus().toString());
            updateShift.setString(2, timeCard.getLocation());
            updateShift.setString(3, timeCard.getNotes());
            updateShift.setBoolean(4, timeCard.getIsTimeCardSubmitted());
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

    @Override
    public boolean updateTimeCard(TimeCard timeCard) {
        try {

            String shiftSQL = "update Shift set Location = ?, Notes = ?, isTimeCardSubmitted = ? where User = ? and ID = ?";
            String userTaskSQL = "insert into UserTask (User, Shift, Task, Count) values (?, ?, ?, ?)";

            con.setAutoCommit(false);

            PreparedStatement updateShift = con.prepareStatement(shiftSQL);
            updateShift.setString(1, timeCard.getLocation());
            updateShift.setString(2, timeCard.getNotes());
            updateShift.setBoolean(3, timeCard.getIsTimeCardSubmitted());
            updateShift.setString(4, timeCard.getUsername());
            updateShift.setLong(5, timeCard.getShiftId());

            updateShift.execute();

            // Remove data for that specific user-shift combination
            clearUserTaskRecords(timeCard.getUsername(), timeCard.getShiftId());

            // Enter new records for that user-shift combination
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
            List<Task> allTasks = jdbcTemplate.query(taskSQL, new TaskRowMapper());

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

    private void clearUserTaskRecords(String username, long shiftId) throws SQLException {

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

    private int getShiftMinutes(long shift_id) throws SQLException{
        String sql = "select StartTime, EndTime from Shift where ID = ?";

        double difference = jdbcTemplate.queryForObject(sql, new Object[]{shift_id}, (resultSet, i) -> {
            Date startTime = (resultSet.getDate("StartTime"));
            Date endTime = (resultSet.getDate("EndTime"));

            return (double) (endTime.getTime() - startTime.getTime()) * 1000;    // difference in seconds
        });

        int minutes = (int) difference / 60;   // convert seconds to minutes

        return minutes;
    }
}