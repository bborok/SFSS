package com.zeta.Data.Statistics;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Models.Campus;
import com.zeta.Models.Task;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatisticsDao implements StatisticsData{

    private JdbcTemplate jdbcTemplate;

    public StatisticsDao() {
        PersistenceConfig config = new PersistenceConfig();
        this.jdbcTemplate = new JdbcTemplate(new PersistenceConfig().dataSource());
    }

    @Override
    public int getTaskCountYear(String taskName, String area, Calendar year) {
        int count = 0;
        String sql = "select UserTask.Task, Shift.Date, Shift.Campus, UserTask.Count from UserTask inner join Shift on UserTask.Shift = Shift.ID " +
                "where UserTask.Task = ?";
        try {
            count = (int)jdbcTemplate.query(sql, new Object[] {taskName}, new ResultSetExtractor() {
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    int count = 0;
                    while (rs.next()) {
                        Date d = rs.getDate("Date");
                        int tmpc = rs.getInt("Count");
                        String tmpa = rs.getString("Campus");
                        //System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(d);
//                        System.out.println(calendar.get(Calendar.YEAR));
//                        System.out.println(calendar.get(Calendar.MONTH));
//                        System.out.println(year.get(Calendar.YEAR));
//                        System.out.println(year.get(Calendar.MONTH));
//                        System.out.println(area.toLowerCase());
//                        System.out.println(tmpa.toLowerCase());
                        if ( calendar.get(Calendar.YEAR) == year.get(Calendar.YEAR) && area.toLowerCase().equals(tmpa.toLowerCase())) {
                            count += tmpc;
                        }
                    }
                    System.out.println("getYear" + String.valueOf(count));
                    return count;
                }
            });

        } catch (Exception e) {
            return 0;
        }
        return count;
    }

    @Override
    public int getTaskCountMonth(String taskName, String area, Calendar month) {
        int count = 0;
        String sql = "select UserTask.Task, Shift.Date, Shift.Campus, UserTask.Count from UserTask inner join Shift on UserTask.Shift = Shift.ID " +
                "where UserTask.Task = ?";
        try {
            count = (int)jdbcTemplate.query(sql, new Object[] {taskName}, new ResultSetExtractor() {
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    int count = 0;
                    while (rs.next()) {
                        Date d = rs.getDate("Date");
                        int tmpc = rs.getInt("Count");
                        String tmpa = rs.getString("Campus");
                        //System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(d);
//                        System.out.println(calendar.get(Calendar.YEAR));
//                        System.out.println(calendar.get(Calendar.MONTH));
//                        System.out.println(month.get(Calendar.YEAR));
//                        System.out.println(month.get(Calendar.MONTH));
//                        System.out.println(area.toLowerCase());
//                        System.out.println(tmpa.toLowerCase());
                        if ( calendar.get(Calendar.YEAR) == month.get(Calendar.YEAR) &&
                                calendar.get(Calendar.MONTH) == month.get(Calendar.MONTH) && area.toLowerCase().equals(tmpa.toLowerCase())) {
                            count += tmpc;
                        }
                    }
                    System.out.println("getMonth" + String.valueOf(count));
                    return count;
                }
            });

        } catch (Exception e) {
            return 0;
        }
        return count;
    }

    @Override
    public int getTaskCountRange(String taskName, String area, Calendar startDate, Calendar endDate) {
        int count = 0;
        String sql = "select UserTask.Task, Shift.Date,  Shift.Campus, UserTask.Count from UserTask inner join Shift on UserTask.Shift = Shift.ID " +
                "where UserTask.Task = ? and Shift.Date >= ? and Shift.Date <= ?";
        try {
            count = (int)jdbcTemplate.query(sql, new Object[] {taskName, startDate.getTime(), endDate.getTime()}, new ResultSetExtractor() {
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    int count = 0;
                    while (rs.next()) {
                        Date d = rs.getDate("Date");
                        int tmpc = rs.getInt("Count");
                        String tmpa = rs.getString("Campus");
                        System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
                        if (area.toLowerCase().equals(tmpa.toLowerCase())) {
                            count += tmpc;
                        }
                    }
                    return count;
                }
            });

        } catch (Exception e) {
            return 0;
        }
        return count;
    }

    @Override
    public int getTaskCountDay(String taskName, String area, Calendar date) {
        int count = 0;
        String sql = "select UserTask.Task, Shift.Date,  Shift.Campus, UserTask.Count from UserTask inner join Shift on UserTask.Shift = Shift.ID " +
                "where UserTask.Task = ? and Shift.Date = ?";
        try {
            count = (int)jdbcTemplate.query(sql, new Object[] {taskName, date.getTime()}, new ResultSetExtractor() {
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    int count = 0;
                    while (rs.next()) {
                        Date d = rs.getDate("Date");
                        int tmpc = rs.getInt("Count");
                        String tmpa = rs.getString("Campus");
                        System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
                        if (area.toLowerCase().equals(tmpa.toLowerCase())) {
                            count += tmpc;
                        }
                    }
                    return count;
                }
            });

        } catch (Exception e) {
            return 0;
        }
        return count;
    }

    @Override
    public List<String> getListOfAllTasksWithoutCounts() {
        List<String> retd = new ArrayList<>();
        try {
            String selectTaskListSQL = "select Task from UserTask";
            // Create time card and set campus, location, notes
            jdbcTemplate.query(selectTaskListSQL, new RowCallbackHandler(){
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    String tmp = rs.getString("Name");
                    retd.add(tmp);
                }
            });
        } catch (Exception e) {
            return null;
        }
        return retd;
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
