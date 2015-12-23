/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.facade.AppointmentFacade;
import org.woofenterprise.dogs.facade.DogFacade;

/**
 *
 * @author michal
 */
@Controller
@RequestMapping("/appointments")
public class AppointmentsController {
    
    @Inject
    AppointmentFacade facade;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<AppointmentDTO> appointments = facade.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/list";
    }
}
