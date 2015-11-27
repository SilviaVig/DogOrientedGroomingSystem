package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Customer;

import java.util.Collection;

/**
 *  An interface that defines a service access to the {@link Customer} entity.
 *  
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface CustomerService {

    /**
     * Finds customer with specific id.
     * @param customerId id of customer
     * @return Customer entity with id
     */
    Customer findCustomerById(Long customerId);

    /**
     * Finds customer with specific email.
     * @param email email of customer
     * @return Customer entity with email
     */
    Customer findCustomerByEmail(String email);

    /** 
     *  Creates Customer.
     * @param customer customer to create
     */
    void createCustomer(Customer customer);

    /**
     * Deletes Customer.
     * @param customer customer to delete
     */
    void deleteCustomer(Customer customer);

    /**
     * Retrieves all Customers.
     * @return all customers
     */
    Collection<Customer> getAllCustomers();
}
