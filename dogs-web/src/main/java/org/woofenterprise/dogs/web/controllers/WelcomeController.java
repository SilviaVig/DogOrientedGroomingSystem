/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.facade.AppointmentFacade;
import org.woofenterprise.dogs.facade.CustomerFacade;

/**
 *
 * @author michal
 */
@Controller
@RequestMapping("/")
public class WelcomeController {
    
    @Inject
    AppointmentFacade facade;

    final static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping("/")
    public String welcomePage(Model model) {
        List<AppointmentDTO> appointments = new ArrayList<>(facade.findAllAppointmentsForToday());
        Collections.sort(appointments, new Comparator<AppointmentDTO>() {

            @Override
            public int compare(AppointmentDTO o1, AppointmentDTO o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });
        model.addAttribute("appointments", appointments);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority auth : authentication.getAuthorities()) {

            log.warn("SECURITY PRINCIPLES ROLES: {}", auth.getAuthority());
        }
        return "welcome";
    }
}
