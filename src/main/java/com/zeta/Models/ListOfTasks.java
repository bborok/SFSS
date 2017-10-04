package com.zeta.Models;

import java.util.ArrayList;
import java.util.List;

/*
 * com.zeta.Models.ListOfTasks class contains a list of all tasks possible on a time card
 * Only the admin or supervisor can add/edit/remove any tasks from this list
 */
public class ListOfTasks {

    private List<Task> tasks;

    public ListOfTasks() {
        tasks = new ArrayList<Task>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public List<Task> getSpecificTask(Task task) {
    List<Task> list = new ArrayList<Task>();

        for (Task t : tasks) {
            if (t.isSameAsOtherTask(task)) {
                list.add(t);
            }
        }

        return list;
    }

    // TODO: should sent an email to supervisor
    // If there are duplicate tasks they will be deleted
    public void removeSpecificTask(Task task) {
        for (Task t : tasks) {
            if (t.isSameAsOtherTask(task)) {
                tasks.remove(t);
            }
        }
    }
}