package org.woofenterprise.dogs.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.woofenterprise.dogs.dto.CustomerCreateDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;
import org.woofenterprise.dogs.rest.ApiUris;
import org.woofenterprise.dogs.rest.exceptions.ResourceAlreadyExistsException;
import org.woofenterprise.dogs.rest.exceptions.ResourceNotFoundException;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Silvia.Vigasova
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_CUSTOMERS)
public class CustomersController {

    final static Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Inject
    CustomerFacade customerFacade;

    /**
     * Get list of Customers curl -i -X GET
     * http://localhost:8080/pa165/rest/customers
     *
     * @return CustomerDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CustomerDTO> getCustomers() {
        logger.debug("rest getCustomers()");
        return customerFacade.getAllCustomers();
    }

    /**
     * Get Customer by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/customers/1
     *
     * @param id identifier for a Customer
     * @return CustomerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getCustomer({})", id);
        CustomerDTO customerDTO = customerFacade.findCustomerById(id);
        if (customerDTO != null) {
            return customerDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Get Customer by email curl -i -X GET
     * http://localhost:8080/pa165/rest/customers/email/neco@neco.cz
     *
     * @param email email of a Customer
     * @return CustomerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomerByEmail(@PathVariable("email") String email) throws Exception {
        logger.debug("rest getCustomer({})", email);
        CustomerDTO customerDTO = customerFacade.findCustomerByEmail(email);
        if (customerDTO != null) {
            return customerDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete one Customer by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/customers/1
     *
     * @param id identifier for Customer
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteCustomer({})", id);
        try {
            CustomerDTO customerDTO = customerFacade.findCustomerById(id);
            customerFacade.deleteCustomer(customerDTO);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new Customer by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data
     * '{"name":"Feri","surname":"Mrkvicka", "email":"feri.mrkvicka@neco.com", "addressFirstLine":"bla",
     * "addressCity":"Tramtaria", "addressCountry":"Narnia", "addressPostalCode":"62400"}'
     * http://localhost:8080/pa165/rest/customers/create
     *
     * @param customer CustomerCreateDTO with required fields for creation
     * @return the created customer CustomerDTO
     * @throws ResourceAlreadyExistsException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO createCustomer(@RequestBody CustomerCreateDTO customer) throws Exception {
        try {
            Long id = customerFacade.createCustomer(customer);
            return customerFacade.findCustomerById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }
}
