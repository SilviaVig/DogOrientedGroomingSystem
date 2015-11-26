package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Customer;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface CustomerService {
    Customer findCustomerById(Long customerId);

    Customer findCustomerByEmail(String email);

    Long createCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Collection<Customer> getAllCustomers();

    void addDog(Long customerId, Long dogId);
}
