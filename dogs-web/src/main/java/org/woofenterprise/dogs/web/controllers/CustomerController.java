package org.woofenterprise.dogs.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.woofenterprise.dogs.dto.CustomerCreateDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

/**
 * @author Silvia.Vigasova
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Inject
    private CustomerFacade customerFacade;

    final static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String list(Model model) {
        Collection<CustomerDTO> customers = customerFacade.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        CustomerDTO customerDTO = customerFacade.findCustomerById(id);
        customerFacade.deleteCustomer(customerDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Customer \""
                + customerDTO.getName()
                + " "
                + customerDTO.getSurname()
                + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/customers/").build().encode().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerFacade.findCustomerById(id));
        return "customers/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("customerCreate", new CustomerCreateDTO());
        return "customers/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("customerCreate") CustomerCreateDTO formBean, BindingResult bindingResult,
                         Model model, UriComponentsBuilder uriBuilder){
        log.error("create customer(formBean={})", formBean);

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "/customers/create";
        }
        Long id = customerFacade.createCustomer(formBean);

        return "redirect:" + uriBuilder.path("/customers/").buildAndExpand(id).encode().toUriString();
    }

}