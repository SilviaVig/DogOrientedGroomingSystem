package org.woofenterprise.dogs.web.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.woofenterprise.dogs.DogsPersistenceApplication;
import org.woofenterprise.dogs.dao.CustomerDAO;
import org.woofenterprise.dogs.facade.CustomerFacade;
import org.woofenterprise.dogs.service.CustomerService;

/**
 *
 * @author michal
 */
@Configuration
@ComponentScan(basePackageClasses = {
    CustomerFacade.class,
    CustomerService.class,
    CustomerDAO.class
}) 
@Import(DogsPersistenceApplication.class)
class DogsConfiguration {
    
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
    
}
