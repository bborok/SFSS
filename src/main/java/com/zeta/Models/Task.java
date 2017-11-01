package com.zeta.Models;

/*
 * com.zeta.Models.Task class is an object that has a string
 */
public class Task {

    private String taskName;
    private int count;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean isSameAsOtherTask(Task t) {
        return taskName.equals(t.getTaskName());
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}