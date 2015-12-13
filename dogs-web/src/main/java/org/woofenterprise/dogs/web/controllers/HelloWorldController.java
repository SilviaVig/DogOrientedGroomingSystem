/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.woofenterprise.dogs.facade.CustomerFacade;

/**
 *
 * @author michal
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    
    @Inject
    CustomerFacade facade;
    
    @RequestMapping("/world")
    @ResponseBody
    public String world() {
        return "Hello World!" + facade.getAllCustomers().toString();
    }
}
