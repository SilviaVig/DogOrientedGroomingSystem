package org.woofenterprise.dogs.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.entity.Customer;

/**
 *
 * @author Michal.Babel
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer findCustomerById(Long customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
