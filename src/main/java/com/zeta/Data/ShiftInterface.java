package com.zeta.Data;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;

import java.util.List;

public interface ShiftInterface {
    //Functions when working with Shift's
    List<Shift> getShifts();
    Shift getShift(long id);
    //Functions when working with ShiftRaw's

    List<ShiftRaw> getShiftRaws();
    boolean saveShift(ShiftRaw shiftRaw);

    //Type agnostic methods
    boolean deleteShift(long id);
}
