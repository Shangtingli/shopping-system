package com.springboot.project.onlineShop.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.springboot.project.onlineShop.util.EntityManagerFactoryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = {"com.springboot.project.onlineShop.repository"})
public class DatabaseConfig{
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:8889/onlineshop");

        return dataSource;
    }
//
//    //TODO: Issue: After Commenting This Out, The bug: Object: is not a valid entity disappear.
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        return EntityManagerFactoryProvider.get(dataSource, "com.springboot.project.onlineShop.model");
//    }
//
//
//    @Bean(name = "transactionManager")
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}