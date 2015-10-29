package org.woofenterprise.dogs.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woofenterprise.dogs.DogsPersistenceApplication;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.utils.Address;

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

    @Test
    public void create() {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        Address address =  new Address.Builder()
                .setCity("city")
                .setCode("code")
                .setCountry("country")
                .setFirstLine("first line")
                .setSecondLine("Second line")
                .build();
        customer.setAddress(address);
        customerDAO.create(customer);
        assertNotNull(customer.getId());
    }

    @Test
    public void findAll() {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        Address address =  new Address.Builder()
                .setCity("city")
                .setCode("code")
                .setCountry("country")
                .setFirstLine("first line")
                .setSecondLine("Second line")
                .build();
        customer.setAddress(address);
        customerDAO.create(customer);

        Customer customer2 = new Customer();
        customer2.setName("Jane");
        customer2.setSurname("Smith");
        Address address2 =  new Address.Builder()
                .setCity("city2")
                .setCode("code2")
                .setCountry("country2")
                .setFirstLine("first line2")
                .setSecondLine("Second line2")
                .build();
        customer2.setAddress(address2);
        customerDAO.create(customer2);

        List<Customer> result = customerDAO.findAll();


        Customer rc = new Customer();
        rc.setName("John");
        rc.setSurname("Doe");
        Address rcAddress =  new Address.Builder()
                .setCity("city")
                .setCode("code")
                .setCountry("country")
                .setFirstLine("first line")
                .setSecondLine("Second line")
                .build();
        rc.setAddress(rcAddress);
        customerDAO.create(rc);

        Customer rc2 = new Customer();
        rc2.setName("Jane");
        rc2.setSurname("Smith");
        Address rcAddress2 =  new Address.Builder()
                .setCity("city2")
                .setCode("code2")
                .setCountry("country2")
                .setFirstLine("first line2")
                .setSecondLine("Second line2")
                .build();
        rc2.setAddress(rcAddress2);
        customerDAO.create(rc2);

        assertEquals(2, result.size());
        assertTrue(result.contains(rc));
        assertTrue(result.contains(rc2));
    }

    @Test
    public void findById() {
        Customer rc2 = new Customer();
        rc2.setName("Jane");
        rc2.setSurname("Smith");
        Address rcAddress2 =  new Address.Builder()
                .setCity("city2")
                .setCode("code2")
                .setCountry("country2")
                .setFirstLine("first line2")
                .setSecondLine("Second line2")
                .build();
        rc2.setAddress(rcAddress2);
        customerDAO.create(rc2);

        Customer result = customerDAO.findById(rc2.getId());

        assertNotNull(result);
        assertEquals(rc2.getName(), result.getName());
    }
    
    
    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        Address address =  new Address.Builder()
                .setCity("city")
                .setCode("code")
                .setCountry("country")
                .setFirstLine("first line")
                .setSecondLine("Second line")
                .build();
        customer.setAddress(address);
        customerDAO.create(customer);

        Customer customer2 = new Customer();
        customer2.setName("Jane");
        customer2.setSurname("Smith");
        Address address2 =  new Address.Builder()
                .setCity("city2")
                .setCode("code2")
                .setCountry("country2")
                .setFirstLine("first line2")
                .setSecondLine("Second line2")
                .build();
        customer2.setAddress(address2);
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
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        Address address =  new Address.Builder()
                .setCity("city")
                .setCode("code")
                .setCountry("country")
                .setFirstLine("first line")
                .setSecondLine("Second line")
                .build();
        customer.setAddress(address);
        customerDAO.create(customer);

        Customer rc2 = new Customer();
        rc2.setName("Jane");
        rc2.setSurname("Smith");
        Address rcAddress2 =  new Address.Builder()
                .setCity("city2")
                .setCode("code2")
                .setCountry("country2")
                .setFirstLine("first line2")
                .setSecondLine("Second line2")
                .build();
        rc2.setAddress(rcAddress2);
        customerDAO.create(rc2);

        List<Customer> result = customerDAO.findAll();
        assertEquals(2, result.size());

        customerDAO.delete(rc2);

        result = customerDAO.findAll();
        assertEquals(1, result.size());

        Customer r = result.get(0);
        assertEquals(customer, r);
    }
}
