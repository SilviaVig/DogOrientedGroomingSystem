/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import org.woofenterprise.dogs.web.exceptions.ResourceNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.facade.AppointmentFacade;
import org.woofenterprise.dogs.facade.DogFacade;
import org.woofenterprise.dogs.utils.Procedure;
import static org.woofenterprise.dogs.web.controllers.CustomerController.log;

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
        
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDog(dog);
        model.addAttribute("proceduresOptions", Procedure.values());
        model.addAttribute("appointment", appointmentDTO);
        return "appointments/calculate";
    }
    
    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String calculate(@ModelAttribute AppointmentDTO appointmentDTO, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        
        Long dogId = appointmentDTO.getDog().getId();
        DogDTO dog = dogFacade.findDogById(dogId);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        appointmentDTO.setDog(dog);
        
        Long duration = facade.calculateAppointmentDuration(appointmentDTO) * 60 * 1000; // in milisecond
        Date endTime = new Date(appointmentDTO.getStartTime().getTime() + duration);        
        appointmentDTO.setEndTime(endTime);

        model.addAttribute("proceduresOptions", Procedure.values());
        model.addAttribute("appointment", appointmentDTO);
        
        return "appointments/calculated";
    }
    
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute AppointmentDTO appointmentDTO, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            
            appointmentDTO = facade.createAppointment(appointmentDTO);
            Long id = appointmentDTO.getId();
            
            return "redirect:" + uriBuilder.path("/appointments/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        catch(Exception ex) {
            Long dogId = appointmentDTO.getDog().getId();
            DogDTO dog = dogFacade.findDogById(dogId);
            if (dog == null) {
                throw new ResourceNotFoundException();
            }
            appointmentDTO.setDog(dog);
            
            model.addAttribute("appointment", appointmentDTO);
            model.addAttribute("proceduresOptions", Procedure.values());
            
            redirectAttributes.addFlashAttribute("alert_danger", "Appointment was not created. " + ex.getLocalizedMessage());
            return "appointments/calculated";
        }
    }
    
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        AppointmentDTO appointmentDTO = facade.findAppointmentById(id);
        if (appointmentDTO == null) {
            throw new ResourceNotFoundException();
        }
        
        model.addAttribute("appointment", appointmentDTO);
        model.addAttribute("proceduresOptions", Procedure.values());
        return "appointments/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute AppointmentDTO appointmentDTO,  Model model, UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "appointments/edit";
        } 
        facade.updateAppointment(appointmentDTO);
        Long id = appointmentDTO.getId();

        return "redirect:" + uriBuilder.path("/appointments/view/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}

