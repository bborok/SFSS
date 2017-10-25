package com.zeta.Data.TimeCard;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Models.Task;
import com.zeta.Models.TimeCard;
import com.zeta.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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
        TimeCard timeCard = null;
        try {
            String shiftSQL = "select Campus, Location, Notes from Shift where User = ? and ID = ?";
            String taskSQL = "select Name from Task where isDeactivated = 0 order by Name asc";
            String userTaskSQL = "select Task, Count from UserTask where User = 'user1' and Shift = 1 order by Task asc";

            // Create time card and set campus, location, notes
            timeCard = jdbcTemplate.queryForObject(shiftSQL, new Object[] {username, shiftId}, new TimeCardRowMapper());

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

            timeCard.setTasks(allTasks);

        } catch (Exception e) {
            return null;
        }
        return timeCard;
    }
}