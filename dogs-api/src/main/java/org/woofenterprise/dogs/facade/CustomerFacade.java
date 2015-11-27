package org.woofenterprise.dogs.facade;

import org.woofenterprice.dogs.dto.CustomerDTO;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface CustomerFacade {

    /**
     *
     * @param customerId
     * @return
     */
    CustomerDTO findCustomerById(Long customerId);

    /**
     *
     * @param email
     * @return
     */
    CustomerDTO findCustomerByEmail(String email);
    
    /**
     *
     * @param customerDTO
     */
    void createCustomer(CustomerDTO customerDTO);
    
    /**
     *
     * @param customerDTO
     */
    void deleteCustomer(CustomerDTO customerDTO);

    /**
     *
     * @return
     */
    Collection<CustomerDTO> getAllCustomers();
    
}
