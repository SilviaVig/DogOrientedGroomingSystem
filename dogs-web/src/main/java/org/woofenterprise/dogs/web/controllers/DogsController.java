/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import java.util.Collection;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;
import org.woofenterprise.dogs.facade.DogFacade;
import static org.woofenterprise.dogs.web.controllers.CustomerController.log;
import org.woofenterprise.dogs.web.exceptions.ResourceNotFoundException;

/**
 *
 * @author michal
 */
@Controller
@RequestMapping("/dogs")
public class DogsController {
    
    @Inject
    DogFacade facade;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        Collection<DogDTO> dogs = facade.getAllDogs();
        model.addAttribute("dogs", dogs);
        return "dogs/list";
    }
    
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") long id, Model model) {
        DogDTO dog = facade.findDogById(id);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("dog", dog);
        return "dogs/view";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            DogDTO dog = facade.findDogById(id);if (dog == null) {
                throw new ResourceNotFoundException();
            }
            facade.deleteDog(dog);
            redirectAttributes.addFlashAttribute("alert_success", "Dog #"+id+" was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Dog #"+id+" was not deleted. " + ex.getLocalizedMessage());
        }
        
        return "redirect:" + uriBuilder.path("/dogs/").build().encode().toUriString();
    }
    
    @Inject 
    private CustomerFacade customerFacade;
    
    @RequestMapping(value = "/new/customer/{customerId}", method = RequestMethod.GET)
    public String create(@PathVariable long customerId, Model model) {
        
        CustomerDTO customer = customerFacade.findCustomerById(customerId);
        if (customer == null) {
            throw new ResourceNotFoundException();
        }
        
        model.addAttribute("customer", customer);
        DogDTO dog = new DogDTO();
        dog.setOwner(customer);
        model.addAttribute("dog", dog);
        model.addAttribute("action", "/dogs/new");
        return "dogs/create";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute DogDTO dogDTO, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "/dogs/create";
        }
            
        dogDTO = facade.createDog(dogDTO);
        Long id = dogDTO.getId();
        return "redirect:" + uriBuilder.path("/dogs/view/{id}").buildAndExpand(id).encode().toUriString();
       
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        DogDTO dog = facade.findDogById(id);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        //model.addAttribute("customer", dog.getOwner());
        model.addAttribute("dog", dog);
        model.addAttribute("action", "/dogs/edit");
        return "dogs/edit";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute DogDTO dogDTO,  Model model, UriComponentsBuilder uriBuilder,RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "/dogs/edit";
        } 
        facade.updateDog(dogDTO);
        Long id = dogDTO.getId();

        return "redirect:" + uriBuilder.path("/dogs/view/{id}").buildAndExpand(id).encode().toUriString();
    }
    
}
