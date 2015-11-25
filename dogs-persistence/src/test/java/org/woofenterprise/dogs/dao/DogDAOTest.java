package org.woofenterprise.dogs.dao;

import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.DogsPersistenceApplication;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;

import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createDog;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createCustomer;

/**
 * Test for {@link DogDAOImpl} class.
 *
 * @author michal.babel@embedit.cz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DogsPersistenceApplication.class)
@Transactional
public class DogDAOTest {

    @Inject
    private DogDAO dogDAO;
    @Inject
    private CustomerDAO customerDAO;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void create() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Dog d = createDog();
        d.setOwner(customer);
        dogDAO.create(d);
        assertNotNull(d.getId());
    }
    
    @Test
    public void createWithNullOwner() {
        Dog d = createDog();
        d.setOwner(null);
        
        thrown.expect(DataIntegrityViolationException.class);
        dogDAO.create(d);
    }

    @Test
    public void findAll() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Dog d1 = createDog();
        d1.setOwner(customer);
        dogDAO.create(d1);

        Dog d2 = createDog(1);
        d2.setOwner(customer);
        dogDAO.create(d2);

        List<Dog> result = dogDAO.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(d1));
        assertTrue(result.contains(d2));
    }

    @Test
    public void findById() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Dog d1 = createDog();
        d1.setOwner(customer);
        dogDAO.create(d1);

        Dog result = dogDAO.findById(d1.getId());

        assertNotNull(result);
        assertEquals(d1.getName(), result.getName());
    }

    @Test
    public void update() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Dog d1 = createDog();
        d1.setOwner(customer);
        dogDAO.create(d1);

        Dog d2 = createDog(1);
        d2.setOwner(customer);
        d2.setHobbies(null);
        dogDAO.create(d2);

        d1.setHobbies("sleeping");
        dogDAO.update(d1);
        
        Dog rd1 = dogDAO.findById(d1.getId());
        assertEquals(d1, rd1);
        assertEquals("sleeping", rd1.getHobbies());
        
        Dog rd2 = dogDAO.findById(d2.getId());
        assertEquals(d2, rd2);
        assertNull(rd2.getHobbies());
    }
    
    @Test
    public void delete() {
        Customer customer = createCustomer();
        customerDAO.create(customer);

        Dog d1 = createDog();
        d1.setOwner(customer);
        dogDAO.create(d1);

        Dog d2 = createDog(1);
        d2.setOwner(customer);
        dogDAO.create(d2);

        List<Dog> result = dogDAO.findAll();
        assertEquals(2, result.size());

        dogDAO.delete(d2);

        result = dogDAO.findAll();
        assertEquals(1, result.size());

        Dog r = result.get(0);
        assertEquals(d1, r);
    }
}
