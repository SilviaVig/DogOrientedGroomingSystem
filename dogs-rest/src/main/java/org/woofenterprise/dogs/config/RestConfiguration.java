package org.woofenterprise.dogs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by silvia.vigasova on 17.12.2015.
 */

@Configuration
@Import({FacadeConfiguration.class, SampleDataConfig.class})
@EnableWebMvc
public class RestConfiguration {

}
