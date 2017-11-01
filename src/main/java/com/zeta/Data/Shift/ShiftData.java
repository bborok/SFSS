package com.zeta.Data.Shift;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;

import java.util.List;

/**
 * Interface to save ShiftRaw's to the database
 */
public interface ShiftData {
    List<ShiftRaw> getShiftRaws();
    ShiftRaw getShiftRaw(long id);
    boolean saveShiftRaw(ShiftRaw shiftRaw);

    boolean deleteShift(long id);
}
