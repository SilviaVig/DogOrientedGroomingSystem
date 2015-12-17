package org.woofenterprise.dogs.config;

/**
 * Created by silvia.vigasova on 17.12.2015.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.woofenterprise.dogs.facade.AppointmentFacade;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses={ AppointmentFacade.class})
public class FacadeConfiguration {
}
