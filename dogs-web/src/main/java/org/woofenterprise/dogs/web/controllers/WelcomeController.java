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
        return "welcome";
    }
}
