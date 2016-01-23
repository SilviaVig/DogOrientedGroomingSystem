package org.woofenterprise.dogs.dao;

import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Customer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public Customer create(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        customer = em.merge(customer);
        em.remove(customer);
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer update(Customer customer) {
        Customer c = findById(customer.getId());
        customer.setPasswordHash(c.getPasswordHash());
        customer.setAdmin(c.isAdmin());
        return em.merge(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        try{
        return em.createQuery("select c from Customer c where c.email = :email", Customer.class)
                .setParameter("email", email)
                .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
