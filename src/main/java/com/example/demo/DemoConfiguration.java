package com.example.demo;

import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableConfigurationProperties(value = { RepositoryRestProperties.class })
@EntityScan({ "com.example.demo.entity.**" })
@EnableJpaRepositories(basePackages = { "com.example.demo.repository.jpa" }, repositoryFactoryBeanClass = DemoJpaRepositoryFactoryBean.class)
public class DemoConfiguration {
}
