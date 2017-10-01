package com.zeta.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *Searches for repositories in the bacePackages paramater of @EnableJpaRepositories
 * that will use JPA.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.zeta.Repositories")
public class JPAConfiguration {
}