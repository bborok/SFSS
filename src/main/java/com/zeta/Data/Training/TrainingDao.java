package com.zeta.Data.Training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrainingDao implements TrainingData {

    private JdbcTemplate jdbcTemplate;
    private Connection con;

    @Autowired
    public TrainingDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            this.con = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
    @Override
    public List<String> getListOfTraining() {
        String sql = "SELECT * FROM Training";
        return jdbcTemplate.query(sql, new TrainingRowMapper());
    }
}
