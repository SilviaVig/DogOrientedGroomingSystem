package org.woofenterprise.dogs.dao;

import org.woofenterprise.dogs.entity.Customer;

import java.util.List;

/**
 * Created by Silvia.Vigasova on 29.10.2015.
 */
public interface CustomerDAO {
    /**
     * Returns customer with specified id.
     *
     * @param id customers id
     * @return customer entity with specified id
     */
    public Customer findById(Long id);
    
    /**
     * Returns customer with specified email address.
     *
     * @param email customers email
     * @return customer entity with specified email address.
     */
    public Customer findByEmail(String email);

    /**
     * Persists customer entity.
     *
     * @param customer customer to persist
     */
    public void create(Customer customer);

    /**
     * Removes a persisted customer.
     *
     * @param customer customer to delete
     */
    public void delete(Customer customer);

    /**
     * Updates a persisted customer.
     *
     * @param customer customer to update
     */
    public void update(Customer customer);
    
    /**
     * Retrieves all persisted customers.
     *
     * @return list of all persisted customers
     */
    public List<Customer> findAll();
}
