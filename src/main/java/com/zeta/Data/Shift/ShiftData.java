package com.zeta.Data.Shift;

import com.zeta.Models.Shift;

import java.util.List;

/**
 * Interface to save Shift's to the database
 */
public interface ShiftData {
    List<Shift> getShifts();

    Shift getShift(long id);

    // Returns a list of all shifts for a specific user
    List<Shift> getShiftsByUser(String username);

    boolean saveShift(Shift shift);

    boolean deleteShift(long id);

    List<Shift> getShiftsWithSubmittedTimeCards();

}
