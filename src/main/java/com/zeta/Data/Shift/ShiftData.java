package com.zeta.Data.Shift;

import com.zeta.Models.ConfirmationStatus;
import com.zeta.Models.Shift;

import java.util.Date;
import java.util.List;

/**
 * Interface to save Shift's to the database
 */
public interface ShiftData {
    List<Shift> getShifts();

    List<Shift> getShiftsWithUsername(String username);

    List<Shift> getShiftsInTimeFrame(Date start, Date end);

    Shift getShift(long id);

    boolean updateAvailability(long id, ConfirmationStatus confirmed);

    // Returns a list of all shifts for a specific user
    List<Shift> getShiftsByUser(String username);

    boolean saveShift(Shift shift);

    boolean deleteShift(long id);

    List<Shift> getShiftsWithSubmittedTimeCards();

}
