package com.dailycode.premiumug.odi.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "OdiEntityManagerFactory",basePackages = {
        "com.dailycode.premiumug.odi.repository"
}, transactionManagerRef = "OdiTransactionManager")
public class OdiDBConfig {
    @Autowired
    Environment env;

    @Bean(name = "OdiDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.odi")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "OdiEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("OdiDatasource")
                                                                           DataSource dataSource){
        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show-sql"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        return builder.dataSource(dataSource).properties(properties).packages("com.dailycode.premiumug.odi.model")
                .persistenceUnit("odi").build();
    }

    @Bean(name = "OdiTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("OdiEntityManagerFactory")
                                                         EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
