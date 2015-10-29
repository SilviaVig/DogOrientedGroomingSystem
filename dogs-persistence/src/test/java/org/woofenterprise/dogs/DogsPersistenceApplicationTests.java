package org.woofenterprise.dogs;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.utils.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DogsPersistenceApplication.class)
public class DogsPersistenceApplicationTests {

    @Inject
    EntityManagerFactory emf;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testEntity() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer c = new Customer();
        c.setName("Jane");
        c.setSurname("Smith");
        c.setAddress(new Address.Builder().setFirstLine("5th Avenue").setCity("New York").setCode("12345").setCountry("USA").build());
        em.persist(c);

        Dog d = new Dog();
        d.setName("Woofie");
        d.setOwner(c);
        em.persist(d);

        em.getTransaction().commit();
        em.close();
    }

}
