package com.sap.ngp.sample.config;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("cloud")
@Configuration
public class PostgresCloudConfig extends AbstractCloudConfig {

    @Bean public DataSource dataSource() {  
        return connectionFactory().dataSource(); 
    }
} 
