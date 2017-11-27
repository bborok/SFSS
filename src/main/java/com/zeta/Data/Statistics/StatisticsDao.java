package com.zeta.Data.Statistics;

import com.zeta.Configurations.PersistenceConfig;
import com.zeta.Models.Campus;
import com.zeta.Models.Task;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticsDao implements StatisticsData{

    private JdbcTemplate jdbcTemplate;

    public StatisticsDao() {
        this.jdbcTemplate = new JdbcTemplate(new PersistenceConfig().dataSource());
    }

    @Override
    public void updateDataForInfoLF(String area, String title, int[] data) {
        try {
            jdbcTemplate.update("update InfoLostFound set M_JAN=?," +
                            "M_FEB=?," +
                            "M_MAR=?," +
                            "M_APR=?," +
                            "M_MAY=?," +
                            "M_JUN=?," +
                            "M_JUL=?," +
                            "M_AUG=?," +
                            "M_SEP=?," +
                            "M_OCT=?," +
                            "M_NOV=?," +
                            "M_DEC=? " +
                            "where CAMPUS=? and TITLE=?",
                    new PreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            for (int i = 0; i < 12; i++) {
                                ps.setInt(i + 1, data[i]);
                            }
                            ps.setString(13, area);
                            ps.setString(14, title);
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int[][] getDataForInfoLF(String area, String[] strs) {
        String sql = "select * from InfoLostFound where CAMPUS = ?";
        int[][] arr = new int[6][12];
        try {
            arr = jdbcTemplate.query(sql, new Object[]{area} , new ResultSetExtractor<int[][]>() {
                public int[][] extractData(ResultSet rs) throws SQLException, DataAccessException {
                    int[][] ret_list = new int[6][12];
                    String[] mons = {   "M_JAN", "M_FEB", "M_MAR",
                                        "M_APR", "M_MAY", "M_JUN",
                                        "M_JUL", "M_AUG", "M_SEP",
                                        "M_OCT", "M_NOV", "M_DEC" };
                    while (rs.next()) {
                        for (int t = 0; t < 6; t++) {
                            if (rs.getString("TITLE").equals(strs[t])) {
                                for (int i = 0; i < 12; i++) {
                                    ret_list[t][i] = rs.getInt(mons[i]);
                                }
                            }
                        }
                    }
                    return ret_list;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return arr;
        }
        return arr;
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
                        System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
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
            e.printStackTrace();
            return 0;
        }
        return count;
    }

    @Override
    public int[][] getDataForPublicContact(String area, String[] strs) {
        int[][] list = new int[6][12];
        String sql = "select UserTask.Task, Shift.Date, Shift.Campus, UserTask.Count from UserTask inner join Shift on UserTask.Shift = Shift.ID ";
        try {
            list = jdbcTemplate.query(sql, new Object[]{} , new ResultSetExtractor<int[][]>() {
                public int[][] extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Date currDate = new Date();
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(currDate);
                    int currYear = ca.get(Calendar.YEAR);
                    int[][] ret_list = new int[6][12];
                    while (rs.next()) {
                        Date d = rs.getDate("Date");
                        int tmpc = rs.getInt("Count");
                        String tmpa = rs.getString("Campus");
                        System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(d);
                        for (int i = 0; i < 6; i++) {
                            if (rs.getString("Task").equals(strs[i])) {
                                for (int j = 0; j < 12; j++) {
                                    String str=String.valueOf(currYear) + "-" + String.valueOf(j+1) + "-01";
                                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = null;
                                    try {
                                        date =sdf.parse(str);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Calendar calendar_tmp = Calendar.getInstance();
                                    calendar_tmp.setTime(date);

                                    if ( calendar.get(Calendar.YEAR) == calendar_tmp.get(Calendar.YEAR) &&
                                            calendar.get(Calendar.MONTH) == calendar_tmp.get(Calendar.MONTH) && area.toLowerCase().equals(tmpa.toLowerCase())) {
                                        ret_list[i][j] += tmpc;
                                        System.out.println(ret_list[i][j]);
                                    }
                                }
                            }
                        }
                    }
                    return ret_list;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
        return list;
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
                        System.out.println(rs.getString("Task") + tmpa + d.toString() + String.valueOf(tmpc));
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
            e.printStackTrace();
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
