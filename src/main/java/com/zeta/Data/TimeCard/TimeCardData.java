package com.zeta.Data.TimeCard;

import com.zeta.Models.TimeCard;

// Every method returns null or false if operation failed
public interface TimeCardData {

    // if timecard is submitted = true then will return false
    public boolean saveTimeCard(TimeCard timeCard);

    // is timecard is submitted = true then will return false because cannot submit more than once
    public boolean submitTimeCard(TimeCard timeCard);

    public boolean updateTimeCard(TimeCard timeCard);
    public boolean timeCardRecordExist(TimeCard timeCard);

    public TimeCard getTimeCard(String username, long shiftId);
}