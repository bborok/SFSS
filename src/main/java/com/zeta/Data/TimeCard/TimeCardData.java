package com.zeta.Data.TimeCard;

import com.zeta.Models.TimeCard;

// Every method returns null or false if operation failed
public interface TimeCardData {

    public boolean saveTimeCard(TimeCard timeCard);

    public boolean submitTimeCard(TimeCard timeCard);

    public boolean updateTimeCard(TimeCard timeCard);

    public TimeCard getTimeCard(String username, long shiftId);
}