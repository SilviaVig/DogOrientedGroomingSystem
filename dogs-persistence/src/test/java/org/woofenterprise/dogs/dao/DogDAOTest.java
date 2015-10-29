package org.woofenterprise.dogs.dao;

import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.DogsPersistenceApplication;
import org.woofenterprise.dogs.entity.Dog;

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

    @Test
    public void create() {
        Dog d = new Dog();
        d.setName("Woofie");
        d.setHobbies("barking");
        dogDAO.create(d);
        assertNotNull(d.getId());
    }

    @Test
    public void findAll() {
        Dog d1 = new Dog();
        d1.setName("Woofie");
        d1.setHobbies("barking");
        dogDAO.create(d1);

        Dog d2 = new Dog();
        d2.setName("Fifi");
        dogDAO.create(d2);

        List<Dog> result = dogDAO.findAll();

        Dog rd1 = new Dog();
        rd1.setName("Woofie");
        rd1.setHobbies("barking");

        Dog rd2 = new Dog();
        rd2.setName("Fifi");

        assertEquals(2, result.size());
        assertTrue(result.contains(rd1));
        assertTrue(result.contains(rd2));
    }

    @Test
    public void findById() {
        Dog d1 = new Dog();
        d1.setName("Woofie");
        d1.setHobbies("barking");
        dogDAO.create(d1);

        Dog result = dogDAO.findById(d1.getId());

        assertNotNull(result);
        assertEquals(d1.getName(), result.getName());
    }

    @Test
    public void update() {
        Dog d1 = new Dog();
        d1.setName("Woofie");
        d1.setHobbies("barking");
        dogDAO.create(d1);

        Dog d2 = new Dog();
        d2.setName("Fifi");
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
        Dog d1 = new Dog();
        d1.setName("Woofie");
        d1.setHobbies("barking");
        dogDAO.create(d1);

        Dog d2 = new Dog();
        d2.setName("Fifi");
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
