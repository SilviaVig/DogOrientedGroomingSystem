/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.web.controllers;

import java.util.Collection;
import javax.annotation.security.RolesAllowed;
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

    @Inject
    private CustomerFacade customerFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listDogs(Model model) {
        Collection<DogDTO> dogs = facade.getAllDogs();
        model.addAttribute("dogs", dogs);
        return "dogs/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewDog(
            @PathVariable("id") long id, Model model) {
        DogDTO dog = facade.findDogById(id);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("dog", dog);
        return "dogs/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @RolesAllowed("ROLE_ADMIN")
    public String deleteDog(@PathVariable("id") long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            DogDTO dog = facade.findDogById(id);
            if (dog == null) {
                throw new ResourceNotFoundException();
            }
            facade.deleteDog(dog);
            redirectAttributes.addFlashAttribute("alert_success", "Dog #" + id + " was deleted.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert_danger", "Dog #" + id + " was not deleted. " + ex.getLocalizedMessage());
        }

        return "redirect:" + uriBuilder.path("/dogs/").build().encode().toUriString();
    }

    @RequestMapping(value = "/new/customer/{customerId}", method = RequestMethod.GET)
    @RolesAllowed("ROLE_ADMIN")
    public String newDog(
            Model model, 
            @PathVariable long customerId
    ) {

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RolesAllowed("ROLE_ADMIN")
    public String createDog(
            Model model, 
            @Valid @ModelAttribute("dog") DogDTO dogDTO, 
            BindingResult bindingResult, 
            UriComponentsBuilder uriBuilder, 
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "dogs/create";
        }
        try{
            dogDTO = facade.createDog(dogDTO);
            Long id = dogDTO.getId();

            redirectAttributes.addFlashAttribute("alert_success", "Dog #" + id + " was created.");
            return "redirect:" + uriBuilder.path("/dogs/view/{id}").buildAndExpand(id).encode().toUriString();
        } catch (Exception e) {
            log.warn("Exception wile creating: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Dog was not created.");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @RolesAllowed("ROLE_ADMIN")
    public String editDog( 
            Model model,
            @PathVariable long id
    ) {
        DogDTO dog = facade.findDogById(id);
        if (dog == null) {
            throw new ResourceNotFoundException();
        }
        
        model.addAttribute("dog", dog);
        model.addAttribute("action", "/dogs/edit");
        return "dogs/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RolesAllowed("ROLE_ADMIN")
    public String updateDog(
            Model model, 
            @Valid @ModelAttribute("dog") DogDTO dogDTO, 
            BindingResult bindingResult, 
            UriComponentsBuilder uriBuilder, 
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "/dogs/edit";
        }
        
        Long id = dogDTO.getId();
        try {
            facade.updateDog(dogDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Dog #" + id + " was updated.");
            return "redirect:" + uriBuilder.path("/dogs/view/{id}").buildAndExpand(id).encode().toUriString();
        } catch (Exception e) {
            log.warn("Exception wile updating: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Dog #" + id + " was not edited.");
            return "redirect:/";
        }
        
    }

}
