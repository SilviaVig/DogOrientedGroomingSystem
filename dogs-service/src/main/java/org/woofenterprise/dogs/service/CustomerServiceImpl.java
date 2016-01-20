package org.woofenterprise.dogs.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.dao.CustomerDAO;
import org.woofenterprise.dogs.entity.Customer;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;

/**
 * @author Silvia Vigasova
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerDAO.findById(customerId);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDAO.create(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }


}
