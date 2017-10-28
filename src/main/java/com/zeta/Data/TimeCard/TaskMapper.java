package com.zeta.Data.TimeCard;

import com.zeta.Models.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Task(resultSet.getString("Name"));
    }
}