package com.puriihuaman.literalura.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.puriihuaman.literalura.domain")
@EnableJpaRepositories("com.puriihuaman.literalura.repos")
@EnableTransactionManagement
public class DomainConfig {
}
