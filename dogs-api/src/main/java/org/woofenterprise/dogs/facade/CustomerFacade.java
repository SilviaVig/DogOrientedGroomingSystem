package org.woofenterprise.dogs.facade;

import org.woofenterprise.dogs.dto.CustomerCreateDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface CustomerFacade {

    /**
     * Finds Customer by id.
     *
     * @param customerId customer id
     * @return customer converted to DTO object
     */
    CustomerDTO findCustomerById(Long customerId);

    /**
     * Finds customer by email.
     *
     * @param email customer email
     * @return customer converted to DTO object
     */
    CustomerDTO findCustomerByEmail(String email);

    /**
     * Creates customer.
     *
     * @param customerDTO customer to create.
     */
    Long createCustomer(CustomerCreateDTO customerDTO);

    /**
     * Deletes customer.
     *
     * @param customerDTO customer to delete.
     */
    void deleteCustomer(CustomerDTO customerDTO);

    /**
     * Retrieves all customers.
     *
     * @return collection of customers converted to DTO objects.
     */
    Collection<CustomerDTO> getAllCustomers();

}
