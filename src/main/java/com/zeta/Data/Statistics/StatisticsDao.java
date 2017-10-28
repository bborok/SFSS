package com.zeta.Data.Statistics;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Models.Campus;
import com.zeta.Models.Task;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.List;

public class StatisticsDao implements StatisticsData{

    private JdbcTemplate jdbcTemplate;

    public StatisticsDao() {
        this.jdbcTemplate = new JdbcTemplate(new PersistenceConfig().dataSource());
    }

    @Override
    public int getTaskCountMonth(String taskName, Calendar month) {
        return 0;
    }

    @Override
    public int getTaskCountRange(String taskName, Calendar startDate, Calendar endDate) {
        return 0;
    }

    @Override
    public int getTaskCountDay(String taskName, Calendar date) {
        return 0;
    }

    @Override
    public List<String> getListOfAllTasksWithoutCounts() {
        return null;
    }

    @Override
    public List<Task> getListOfAllTasksMonth(Calendar month) {
        return null;
    }

    @Override
    public List<Task> getListOfAllTasksRange(Calendar startDate, Calendar endDate) {
        return null;
    }

    @Override
    public List<Task> getListOfAllTasksDate(Calendar date) {
        return null;
    }

    @Override
    public List<Task> getTasksByCampus(Campus campus) {
        return null;
    }

    @Override
    public List<Task> getTasksByUserMonth(String username, Calendar month) {
        return null;
    }
}
