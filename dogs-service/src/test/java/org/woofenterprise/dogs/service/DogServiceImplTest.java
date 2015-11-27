package org.woofenterprise.dogs.service;

import java.util.Collection;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprise.dogs.dao.DogDAO;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.service.utils.BaseTestCase;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class DogServiceImplTest extends BaseTestCase {

    @Mock
    private DogDAO dogDAO;

    @InjectMocks
    private DogServiceImpl dogService;

    @Test
    public void testCreate() {
        Dog d = mock(Dog.class);
        dogService.createDog(d);
        verify(dogDAO).create(d);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        Dog dog = mock(Dog.class);
        when(dogDAO.findById(id)).thenReturn(dog);
        Dog returnedDog = dogService.findDogById(id);
        verify(dogDAO).findById(id);
    }

    @Test
    public void testDelete() {
        Dog dog = mock(Dog.class);
        dogService.deleteDog(dog);
        verify(dogDAO).delete(dog);
    }

    @Test
    public void testGetAll() {
        Collection<Dog> result = dogService.getAllDogs();
        verify(dogDAO).findAll();
    }

}
