package org.woofenterprise.dogs.dao;

import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Customer;

import java.util.List;

/**
 * @author Silvia.Vigasova
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Customer findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
