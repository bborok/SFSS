package com.zeta.Data.TimeCard;

import com.zeta.Models.TimeCard;

// Every method returns null or false if operation failed
public interface TimeCardData {

    // Required data: username, shift id, list of tasks and their amounts, location, notes (comment to be removed later)
    public boolean saveTimeCard(TimeCard timeCard);

    public boolean submitTimeCard(TimeCard timeCard);

    // Required data: username, shift id, list of tasks and their amounts, location, notes (comment to be removed later)
    public boolean updateTimeCard(TimeCard timeCard);

    // Required data: user's username and the shift's id value
    public TimeCard getTimeCard(String username, long shiftId);
}