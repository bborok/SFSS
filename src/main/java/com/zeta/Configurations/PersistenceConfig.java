package com.zeta.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//Configuration local database connection.
//Currently configured to use HibernateJPA.
@Configuration
public class PersistenceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //TODO: Change the parameters in the functions calls below as appropriate.
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?createDatabaseIfNotExist=true");
        dataSource.setUsername("testuser");
        dataSource.setPassword("testtest");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setPackagesToScan("com.zeta.Models"); //scans for classes annotated with @Entity
        emfb.setJpaProperties(hibernateProperteies());
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties hibernateProperteies(){
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        //TODO: Change this to create-update during production. Current this setting will create/drop tables every time the webapp starts.
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","create-drop"); //Setting for generating DDL
//        hibernateProperties.setProperty("hibernate.hbm2ddl.import_files", "import.sql");
        return hibernateProperties;
    }
}
