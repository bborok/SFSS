package com.zeta.Configurations;

import com.zeta.Data.UserDao;
import com.zeta.Data.UserInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//Configuration for local database connection.
@Configuration
public class PersistenceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //TODO: Change the parameters in the functions calls below as appropriate.
        dataSource.setUrl("jdbc:mysql://cmpt373-1177z.cmpt.sfu.ca:3306/testdb2");
        dataSource.setUsername("testuser2".trim());
        dataSource.setPassword("Testuser2".trim());
        return dataSource;
    }

    @Bean
    public UserInterface getUserInterface() {
        return new UserDao(dataSource());
    }
}