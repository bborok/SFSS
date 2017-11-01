package com.zeta.Data.TimeCard;

import com.zeta.Models.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {

        Task task = new Task(resultSet.getString("Task"));
        task.setCount(resultSet.getInt("Count"));

        return task;
    }
}