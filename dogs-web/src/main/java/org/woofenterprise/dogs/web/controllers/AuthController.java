package org.woofenterprise.dogs.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.woofenterprise.dogs.dto.CustomerAuthenticationDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Silvia.Vigasova
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Inject
    private CustomerFacade customerFacade;

    @Inject
    private HttpSession session;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("customer", new CustomerAuthenticationDTO());
        return "auth/login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(Model model, @Valid @ModelAttribute("customer") CustomerAuthenticationDTO customer, UriComponentsBuilder uriBuilder) {
        CustomerDTO c = customerFacade.findCustomerByEmail(customer.getEmail());

        if (customer == null) {
            return "redirect:/";
        }

        if (customerFacade.authenticate(customer)) {
            // authenticated
            if (customerFacade.isAdmin(c)) {
                session.setAttribute("authenticated", "admin");
            } else {
                session.setAttribute("authenticated", "user");
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(RedirectAttributes red, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("authenticated");
        red.addFlashAttribute("Info"," Successfully logged out");
        return "redirect:/";
    }
}