package com.zeta.Data.TimeCard;

import com.zeta.Models.Campus;
import com.zeta.Models.TimeCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeCardRowMapper implements RowMapper<TimeCard> {

    @Override
    public TimeCard mapRow(ResultSet resultSet, int i) throws SQLException {

        TimeCard timeCard = new TimeCard();

        timeCard.setCampus(Campus.valueOf(resultSet.getString("Campus").toUpperCase()));

        if (resultSet.getString("Location") == null) {
            timeCard.setLocation(null);
        } else {
            timeCard.setLocation(resultSet.getString("Location"));
        }

        if (resultSet.getString("Notes") == null) {
            timeCard.setNotes(null);
        } else {
            timeCard.setNotes(resultSet.getString("Notes"));
        }

        if (resultSet.getInt("isTimeCardSubmitted") == 1) {
            timeCard.setTimeCardSubmitted(true);
        } else {
            timeCard.setTimeCardSubmitted(false);
        }
        return timeCard;
    }
}