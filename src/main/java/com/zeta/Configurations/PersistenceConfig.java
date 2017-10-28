package com.zeta.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

//Configuration for local database connection.
@Configuration
public class PersistenceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //TODO: Change the parameters in the functions calls below as appropriate.
        dataSource.setUrl("jdbc:mysql://cmpt373-1177z.cmpt.sfu.ca:3306/testdb");
        dataSource.setUsername("testuser".trim());
        dataSource.setPassword("testtest".trim());
        return dataSource;
    }

    public Connection getConnection () {
        Connection con;
        try {
            con = DriverManager.getConnection (
                    "jdbc:mysql://cmpt373-1177z.cmpt.sfu.ca:3306/testdb", "testuser","testtest");
        } catch (Exception e) {
            return null;
        }
        return con;
    }
}