package org.woofenterprise.dogs.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.woofenterprise.dogs.service.CustomerService;

@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan(basePackageClasses={ CustomerService.class})
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }



}