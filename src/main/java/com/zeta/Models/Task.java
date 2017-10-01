package com.zeta.Models;

/*
 * com.zeta.Models.Task class is an object that has a string
 */
public class Task {

    private String taskName;

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
}