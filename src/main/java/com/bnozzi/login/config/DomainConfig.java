package com.bnozzi.login.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.bnozzi.login.domain")
@EnableJpaRepositories("com.bnozzi.login.repos")
@EnableTransactionManagement
public class DomainConfig {
}
