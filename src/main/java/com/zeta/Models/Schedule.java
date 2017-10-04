package com.zeta.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * com.zeta.Models.Schedule class
 */
public class Schedule {

    private TimePeriod timePeriod;
    private List<Shift> shifts = new ArrayList<Shift>();

    public Schedule(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public List<Shift> getAllShifts() {
        return shifts;
    }

    public List<Shift> getShiftsByRole(Role role) {
        List<Shift> list = new ArrayList<Shift>();

        for (Shift shift : shifts) {
            if (shift.getWorkerRole() == role) {
                list.add(shift);
            }
        }

        return list;
    }

    public List<Shift> getShiftsByTask(Task task) {
        List<Shift> list = new ArrayList<Shift>();

        for (Shift shift : shifts) {
            if (shift.getTask().isSameAsOtherTask(task)) {
                list.add(shift);
            }
        }

        return list;
    }

    public List<Shift> getShiftsForPeriodOfTime(Calendar start, Calendar end) {
        List<Shift> list = new ArrayList<Shift>();

        // Thorough testing needed to make sure objects compare correctly
        for (Shift shift : shifts) {
            if (shift.getStartTime().equals(start) && shift.getEndTime().equals(end)) {
                list.add(shift);
            }
        }

        return list;
    }
}