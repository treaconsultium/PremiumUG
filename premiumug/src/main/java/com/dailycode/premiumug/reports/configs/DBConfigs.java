package com.dailycode.premiumug.reports.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "midbEntityManagerFactory",basePackages = {
        "com.dailycode.premiumug.reports.repository"
}, transactionManagerRef = "midbTransactionManager")
public class DBConfigs {

    @Autowired
    Environment env;

    @Bean(name = "midbDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.midb")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "midbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("midbDatasource")
    DataSource dataSource){
        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show-sql"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.dailycode.premiumug.reports.model")
                .persistenceUnit("reports").build();
    }

    @Bean(name = "midbTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("midbEntityManagerFactory")
                                                         EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
