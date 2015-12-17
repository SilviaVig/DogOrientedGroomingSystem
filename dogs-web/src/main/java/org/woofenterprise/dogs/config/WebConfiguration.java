package org.woofenterprise.dogs.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author michal
 */
@Configuration
@Import(FacadeConfiguration.class)
class WebConfiguration {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
    
}
