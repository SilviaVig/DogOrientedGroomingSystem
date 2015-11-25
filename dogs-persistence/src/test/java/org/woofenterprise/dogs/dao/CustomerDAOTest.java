package org.woofenterprise.dogs.dao;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woofenterprise.dogs.DogsPersistenceApplication;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;

import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createDog;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createCustomer;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

import org.springframework.transaction.annotation.Transactional;

/**
 * Test class for CustomerDAO
 *
 * @author Silvia.Vigasova
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DogsPersistenceApplication.class)
@Transactional
public class CustomerDAOTest {

    @Inject
    private CustomerDAO customerDAO;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void create() {
        Customer customer = createCustomer();
        customerDAO.create(customer);
        assertNotNull(customer.getId());
    }

    @Test
    public void findAll() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Customer customer2 = createCustomer(1);
        customerDAO.create(customer2);

        List<Customer> result = customerDAO.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(customer));
        assertTrue(result.contains(customer2));
    }

    @Test
    public void findById() {
        Customer rc2 = createCustomer();
        customerDAO.create(rc2);

        Customer result = customerDAO.findById(rc2.getId());

        assertNotNull(result);
        assertEquals(rc2.getName(), result.getName());
    }


    @Test
    public void update() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Customer customer2 = createCustomer(1);
        customerDAO.create(customer2);

        customer2.setSurname("Doe");
        customerDAO.update(customer2);

        Customer r1 = customerDAO.findById(customer.getId());
        assertEquals(customer, r1);
        Customer r2 = customerDAO.findById(customer2.getId());
        assertEquals("Doe", r2.getSurname());
    }

    @Test
    public void delete() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Customer rc2 = createCustomer(1);
        customerDAO.create(rc2);

        List<Customer> result = customerDAO.findAll();
        assertEquals(2, result.size());

        customerDAO.delete(rc2);

        result = customerDAO.findAll();
        assertEquals(1, result.size());

        Customer r = result.get(0);
        assertEquals(customer, r);
    }

    @Test
    public void nullAddressNotAllowed() {
        Customer customer = createCustomer();
        customer.setAddressCity(null);
        thrown.expect(DataIntegrityViolationException.class);
        customerDAO.create(customer);
    }

    @Test
    public void nullSurnameNotAllowed() {
        Customer customer = createCustomer();
        customer.setSurname(null);
        thrown.expect(DataIntegrityViolationException.class);
        customerDAO.create(customer);
    }

    @Test
    public void nullNameNotAllowed() {
        Customer customer = createCustomer();
        customer.setName(null);
        thrown.expect(DataIntegrityViolationException.class);
        customerDAO.create(customer);
    }

    @Test
    public void dogsSet() {
        Customer customer = createCustomer();
        Dog d1 = createDog();
        Dog d2 = createDog(1);
        customer.addDog(d1);
        customer.addDog(d2);
        customerDAO.create(customer);

        Customer found = customerDAO.findById(customer.getId());

        Assert.assertEquals(2, found.getDogs().size());

        assertTrue(found.getDogs().contains(d2));
        assertTrue(found.getDogs().contains(d1));
    }
}