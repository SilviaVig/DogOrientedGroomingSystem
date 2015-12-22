package org.woofenterprise.dogs.config;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.woofenterprise.dogs.config.ServiceConfiguration;
import org.woofenterprise.dogs.facade.SampleDataFacade;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataFacade.class})
public class SampleDataConfig {
    
    final static Logger log = LoggerFactory.getLogger(SampleDataConfig.class);
    
    @Inject
    SampleDataFacade sampleDataFacade;
    
    @PostConstruct
    public void loadData() {
        log.debug("Creating sample data");
        sampleDataFacade.createSampleData();
    }
    
}
