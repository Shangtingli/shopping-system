package onlineShop;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ApplicationConfig {

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		System.out.println("sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("onlineShop.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		System.out.println("dataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}
	
	 // Handler for multipart form data
    @Bean
    public MultipartResolver multipartResolver() {
     System.out.println("MultipartResolver");
   	 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
   	 multipartResolver.setMaxUploadSize(10240000);
   	 return multipartResolver;
    }
    
    // Properly encode URL encoding for special characters.
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
    	System.out.println("Firewall");
    	StrictHttpFirewall firewall = new StrictHttpFirewall();
    	firewall.setAllowUrlEncodedSlash(true);
    	firewall.setAllowSemicolon(true);
    	return firewall;
    }

	private final Properties hibernateProperties() {
		System.out.println("Hibernate");
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return hibernateProperties;
	}
}

