package com.springboot.project.onlineShop.config;
import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

import org.eclipse.persistence.jpa.PersistenceProvider;
import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class EntityManagerFactoryProvider {
    public static LocalContainerEntityManagerFactoryBean get(DataSource dataSource, String... packagesToScan) {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setPersistenceProvider(new PersistenceProvider());
        entityManagerFactoryBean.setPackagesToScan(packagesToScan);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaPropertyMap(getJPAProperties(dataSource.getClass().getClassLoader()));
        entityManagerFactoryBean.setLoadTimeWeaver(new SimpleLoadTimeWeaver());
        entityManagerFactoryBean.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());

        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean;
    }


    private static Map<String, Object> getJPAProperties(ClassLoader classLoader) {
        Map<String, Object> properties = new HashMap<>();

        properties.put(DDL_GENERATION, CREATE_OR_EXTEND);
        properties.put(DDL_GENERATION_MODE, DDL_DATABASE_GENERATION);
        properties.put(CLASSLOADER, classLoader);
        properties.put(LOGGING_LEVEL, "INFO"); // "FINE" provides more details

        // do not cache entities locally, as this causes problems if multiple application instances are used
        properties.put(CACHE_SHARED_DEFAULT, "false");
        properties.put(CONNECTION_POOL_MAX, 50);

        return properties;
    }
}
