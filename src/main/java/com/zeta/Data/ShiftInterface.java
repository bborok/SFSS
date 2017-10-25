package com.zeta.Data;

import com.zeta.Models.Shift;
import com.zeta.Models.ShiftRaw;

import java.util.List;

public interface ShiftInterface {
    public List<Shift> getShifts();
    public Shift saveShift(ShiftRaw shiftRaw);
//    public Shift updateShift(ShiftRaw shiftRaw);
    public Shift getShift(long id);
    public Shift deleteShift(long id);
}
