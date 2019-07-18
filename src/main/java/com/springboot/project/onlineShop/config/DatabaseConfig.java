package com.springboot.project.onlineShop.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.springboot.project.onlineShop.model.Authorities;
import com.springboot.project.onlineShop.model.BillingAddress;
import com.springboot.project.onlineShop.model.Cart.Cart;
import com.springboot.project.onlineShop.model.CartItem.CartItem;
import com.springboot.project.onlineShop.model.Customer.Customer;
import com.springboot.project.onlineShop.model.Product.Product;
import com.springboot.project.onlineShop.model.SalesOrder.SalesOrder;
import com.springboot.project.onlineShop.model.ShippingAddress;
import com.springboot.project.onlineShop.model.User;
import com.springboot.project.onlineShop.util.EntityManagerFactoryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = {"com.springboot.project.onlineShop.model"})
public class DatabaseConfig{
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:8889/onlineShop");

        return dataSource;
    }

    //Important: Must incllude all the class names in entity manager factory
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        return EntityManagerFactoryProvider.get(dataSource,
                Customer.class.getPackage().getName(), Cart.class.getPackage().getName(), CartItem.class.getPackage().getName(),
                Product.class.getPackage().getName(), SalesOrder.class.getPackage().getName(),
                Authorities.class.getPackage().getName(), BillingAddress.class.getPackage().getName(),
                ShippingAddress.class.getPackage().getName(), User.class.getPackage().getName()
        );
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}