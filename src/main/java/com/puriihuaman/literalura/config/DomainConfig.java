package com.puriihuaman.literalura.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.puriihuaman.literalura.persistence.domain")
@EnableJpaRepositories("com.puriihuaman.literalura.persistence.repos")
@EnableTransactionManagement
public class DomainConfig {
}