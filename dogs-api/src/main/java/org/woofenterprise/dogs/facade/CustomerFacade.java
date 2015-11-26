package org.woofenterprise.dogs.facade;

import org.woofenterprice.dogs.dto.CustomerDTO;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface CustomerFacade {

    CustomerDTO findCustomerById(Long customerId);

    CustomerDTO findCustomerByEmail(String email);
    
    Long createCustomer(CustomerDTO customerDTO);
    
    void deleteCustomer(CustomerDTO customerDTO);

    Collection<CustomerDTO> getAllCustomers();
    
}
