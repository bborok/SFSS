package com.zeta.Data.Shift;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;

import java.util.List;

/**
 * Interface to save Shift and ShiftRaw's to the database
 */
public interface ShiftInterface {
    //Functions when working with ShiftRaw's
    List<ShiftRaw> getShiftRaws();
    ShiftRaw getShiftRaw(long id);
    boolean saveShiftRaw(ShiftRaw shiftRaw);

    //Type agnostic methods
    boolean deleteShift(long id);
}
