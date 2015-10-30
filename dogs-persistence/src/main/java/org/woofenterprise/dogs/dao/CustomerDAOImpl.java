package org.woofenterprise.dogs.dao;

import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Customer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author michal.babel
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    
    @PersistenceContext
    EntityManager em;
    
    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public void update(Customer customer) {
        /*Customer toUpdate = findById(customer.getId());
        toUpdate.setAddress(customer.getAddress());
        toUpdate.setName(customer.getName());
        toUpdate.setSurname(customer.getSurname());
        toUpdate.setPhoneNumber(customer.getPhoneNumber());
        em.merge(toUpdate);*/
        em.merge(customer);
    }
}
