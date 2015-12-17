/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author michal
 */
@EnableWebMvc
@Configuration
@Import({WebConfiguration.class})
@ComponentScan(basePackages = "org.woofenterprise.dogs.web.controllers")
public class SpringMvcConfig {
    
}
