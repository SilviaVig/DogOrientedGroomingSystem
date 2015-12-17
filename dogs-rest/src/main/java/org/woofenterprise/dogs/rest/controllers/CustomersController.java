package org.woofenterprise.dogs.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;
import org.woofenterprise.dogs.rest.ApiUris;
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
     * http://localhost:8080/eshop-rest/Customers
     *
     * @return CustomerDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CustomerDTO> getCustomers() {
        logger.debug("rest getCustomers()");
        return customerFacade.getAllCustomers();
    }

    /**
     *
     * Get Customer by identifier id curl -i -X GET
     * http://localhost:8080/eshop-rest/Customers/1
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
     * Delete one Customer by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/Customers/1
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
    
}
