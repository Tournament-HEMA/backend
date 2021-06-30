package com.example.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .driverClassName(this.driver).build();
    }

    @Bean
    public NamedParameterJdbcTemplate getTemplate(@Autowired DataSource dataSource)
    {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
