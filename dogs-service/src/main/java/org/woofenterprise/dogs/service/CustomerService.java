package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Customer;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface CustomerService {

    /**
     *
     * @param customerId
     * @return
     */
    Customer findCustomerById(Long customerId);

    /**
     *
     * @param email
     * @return
     */
    Customer findCustomerByEmail(String email);

    /**
     *
     * @param customer
     */
    void createCustomer(Customer customer);

    /**
     *
     * @param customer
     */
    void deleteCustomer(Customer customer);

    /**
     *
     * @return
     */
    Collection<Customer> getAllCustomers();
}
