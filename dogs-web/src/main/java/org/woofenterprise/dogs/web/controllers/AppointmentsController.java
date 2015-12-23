/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import org.woofenterprise.dogs.web.exceptions.ResourceNotFoundException;
import java.util.Collection;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.woofenterprise.dogs.dto.AppointmentCreateDTO;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.facade.AppointmentFacade;
import org.woofenterprise.dogs.facade.DogFacade;
import org.woofenterprise.dogs.utils.Procedure;

/**
 *
 * @author michal
 */
@Controller
@RequestMapping("/appointments")
public class AppointmentsController {
    
    @Inject
    private AppointmentFacade facade;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<AppointmentDTO> appointments = facade.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/list";
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        AppointmentDTO appointment = facade.findAppointmentById(id);
        if (appointment == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("appointment", appointment);
        return "appointments/view";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            AppointmentDTO appointment = facade.findAppointmentById(id);if (appointment == null) {
                throw new ResourceNotFoundException();
            }
            facade.cancelAppointment(appointment);
            redirectAttributes.addFlashAttribute("alert_success", "Appointment #"+id+" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Appointment #"+id+" was not deleted. " + ex.getLocalizedMessage());
        }
        
        return "redirect:" + uriBuilder.path("/appointments/").build().encode().toUriString();
    }
    
    @Inject 
    private DogFacade dogFacade;
    
    @RequestMapping(value = "/new/dog/{dogId}", method = RequestMethod.GET)
    public String create(@PathVariable long dogId, Model model) {
        
        DogDTO dog = dogFacade.findDogById(dogId);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        
        model.addAttribute("dog", dog);
        model.addAttribute("proceduresOptions", Procedure.values());
        AppointmentCreateDTO appointmentCreate = new AppointmentCreateDTO();
        appointmentCreate.setDogId(dogId);
        model.addAttribute("appointmentCreate", appointmentCreate);
        model.addAttribute("action", "/appointments/new");
        return "appointments/form";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute AppointmentCreateDTO appointmentCreateDTO, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            
            AppointmentDTO appointmentDTO = facade.createAppointment(appointmentCreateDTO);
            Long id = appointmentDTO.getId();
            
            return "redirect:" + uriBuilder.path("/appointments/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        catch(Exception ex) {
            Long dogId = appointmentCreateDTO.getDogId();
            DogDTO dog = dogFacade.findDogById(dogId);
            if (dog == null) {
                throw new ResourceNotFoundException();
            }

            model.addAttribute("dog", dog);
            model.addAttribute("proceduresOptions", Procedure.values());
            AppointmentCreateDTO appointmentCreate = new AppointmentCreateDTO();
            appointmentCreate.setDogId(dogId);
            model.addAttribute("appointmentCreate", appointmentCreate);
            redirectAttributes.addFlashAttribute("alert_danger", "Appointment was not created. " + ex.getLocalizedMessage());
            return "appointments/form";
        }
    }
}

