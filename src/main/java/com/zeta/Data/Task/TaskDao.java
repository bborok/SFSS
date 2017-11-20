package com.zeta.Data.Task;

import com.zeta.Models.Campus;
import com.zeta.Models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TaskDao implements TaskData {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getTasks() {
        String sql = "SELECT * FROM Task";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    @Override
    public List<Task> getTasks(Campus campus) {
        String sql;
        if (campus == Campus.VANCOUVER)
            sql = "SELECT * FROM Task WHERE isVancouver = 1";
        else if (campus == Campus.SURREY)
            sql = "SELECT * FROM Task WHERE isSurrey = 1";
        else
            sql = "SELECT * FROM Task WHERE isBurnaby = 1";
//        String sql = "SELECT * FROM Task WHERE isSurrey = 1";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }
}
