package com.zeta.Data.Statistics;

import com.zeta.Models.Campus;
import com.zeta.Models.Task;

import java.util.Calendar;
import java.util.List;

public interface StatisticsData {

    public void updateDataForInfoLF(String area, String strs, int[] data);

    public int[][] getDataForInfoLF(String area, String[] strs);

    public int[][] getDataForPublicContact(String area, String[] strs);

    public int getTaskCountYear(String taskName, String area, Calendar year);

    public int getTaskCountMonth(String taskName, String area, Calendar month);

    public int getTaskCountRange(String taskName, String area, Calendar startDate, Calendar endDate);

    public int getTaskCountDay(String taskName, String area, Calendar date);


    public List<String> getListOfAllTasksWithoutCounts();

    public List<Task> getListOfAllTasksMonth(Calendar month);

    public List<Task> getListOfAllTasksRange(Calendar startDate, Calendar endDate);

    public List<Task> getListOfAllTasksDate(Calendar date);

    public List<Task> getTasksByCampus(Campus campus);

    public List<Task> getTasksByUserMonth(String username, Calendar month);
}