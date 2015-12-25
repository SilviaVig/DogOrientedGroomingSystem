/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import java.util.Collection;
import java.util.Date;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.dto.AppointmentDurationDTO;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.facade.AppointmentFacade;
import org.woofenterprise.dogs.facade.DogFacade;
import org.woofenterprise.dogs.utils.Procedure;
import static org.woofenterprise.dogs.web.controllers.CustomerController.log;
import org.woofenterprise.dogs.web.exceptions.ResourceNotFoundException;

/**
 *
 * @author michal
 */
@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

    private static AppointmentDTO createFromDurationDTO(AppointmentDurationDTO appointmentDurationDTO) {
        AppointmentDTO result = new AppointmentDTO();
        result.setId(appointmentDurationDTO.getId());
        result.setDog(appointmentDurationDTO.getDog());
        result.setStartTime(appointmentDurationDTO.getStartTime());
        result.setEndTime(appointmentDurationDTO.getEndTime());
        result.setProcedures(appointmentDurationDTO.getProcedures());
        return result;
    }
    
    @Inject
    private AppointmentFacade facade;
    
    @Inject 
    private DogFacade dogFacade;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAppointments(Model model) {
        Collection<AppointmentDTO> appointments = facade.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/list";
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewAppointment(
            Model model, @PathVariable("id") long id) {
        AppointmentDTO appointment = facade.findAppointmentById(id);
        if (appointment == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("appointment", appointment);
        return "appointments/view";
    }
    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteAppointment(
            @PathVariable("id") long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
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
    
    
    @RequestMapping(value = "/new/dog/{dogId}", method = RequestMethod.GET)
    public String newAppointment(Model model, @PathVariable long dogId) {
        model.addAttribute("proceduresOptions", Procedure.values());
        
        DogDTO dog = dogFacade.findDogById(dogId);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        
        AppointmentDurationDTO appointmentDTO = new AppointmentDurationDTO();
        appointmentDTO.setDog(dog);
        model.addAttribute("appointment", appointmentDTO);
        return "appointments/calculate";
    }
    
    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String calculateDuration(
            Model model, 
            @Valid @ModelAttribute("appointment") AppointmentDurationDTO appointmentDurationDTO, 
            BindingResult bindingResult, 
            UriComponentsBuilder uriBuilder, 
            RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("proceduresOptions", Procedure.values());
        
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "appointments/calculate";
        } 
        
        AppointmentDTO appointmentDTO = createFromDurationDTO(appointmentDurationDTO);
        
        Long duration = facade.calculateAppointmentDuration(appointmentDTO) * 60 * 1000; // in miliseconds
        Date endTime = new Date(appointmentDTO.getStartTime().getTime() + duration);        
        appointmentDTO.setEndTime(endTime);

        model.addAttribute("appointment", appointmentDTO);
        
        return "appointments/create";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createAppointment(
            Model model, 
            @Valid @ModelAttribute("appointment") AppointmentDTO appointmentDTO, 
            BindingResult bindingResult, 
            UriComponentsBuilder uriBuilder, 
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("proceduresOptions", Procedure.values());
            
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            
            if (bindingResult.hasGlobalErrors()) {
                StringBuilder sb = new StringBuilder();
                for (ObjectError er : bindingResult.getGlobalErrors()) {
                    sb.append(er.getDefaultMessage());
                    sb.append('\n');
                }

                model.addAttribute("globalError", sb);
            }
            
            return "appointments/create";
        } 
        try {
            appointmentDTO = facade.createAppointment(appointmentDTO);
            Long id = appointmentDTO.getId();

            redirectAttributes.addFlashAttribute("alert_success", "Appointment #" + id + " was created.");
            return "redirect:" + uriBuilder.path("/appointments/view/{id}").buildAndExpand(id).encode().toUriString();    
        } catch (Exception e) {
            log.warn("Exception wile creating: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Appointment was not created.");
            return "redirect:/";
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
    public String update(
            Model model, 
            @Valid @ModelAttribute("appointment") AppointmentDTO appointmentDTO, 
            BindingResult bindingResult,
            UriComponentsBuilder uriBuilder,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("proceduresOptions", Procedure.values());
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
                  
            if (bindingResult.hasGlobalErrors()) {
                StringBuilder sb = new StringBuilder();
                for (ObjectError er : bindingResult.getGlobalErrors()) {
                    sb.append(er.getDefaultMessage());
                    sb.append('\n');
                }

                model.addAttribute("globalError", sb);
            }
            
            return "appointments/edit";
        } 
        Long id = appointmentDTO.getId();
        try{
            facade.updateAppointment(appointmentDTO);

            redirectAttributes.addFlashAttribute("alert_success", "Appointment #" + id + " was edited.");
            return "redirect:" + uriBuilder.path("/appointments/view/{id}").buildAndExpand(id).encode().toUriString();
        } catch (Exception e) {
            log.warn("Exception wile editing: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Appointment #" + id + " was not edited.");
            return "redirect:/";
        }
    }
    
}

