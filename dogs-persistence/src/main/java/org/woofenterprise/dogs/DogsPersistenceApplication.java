package org.woofenterprise.dogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample application configuration class.
 *
 * @author michal.babel
 */
@SpringBootApplication
public class DogsPersistenceApplication {

    /**
     * Runs application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DogsPersistenceApplication.class, args);
    }
}
