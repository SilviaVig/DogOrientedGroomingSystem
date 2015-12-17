package org.woofenterprise.dogs.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.woofenterprise.dogs.dao.CustomerDAO;

/**
 * Sample application configuration class.
 *
 * @author michal.babel
 */
@SpringBootApplication
@ComponentScan(basePackageClasses={CustomerDAO.class})
public class PersistenceConfiguration {
}
