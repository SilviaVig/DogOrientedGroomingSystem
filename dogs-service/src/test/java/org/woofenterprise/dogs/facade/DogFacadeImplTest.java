package org.woofenterprise.dogs.facade;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprice.dogs.dto.DogDTO;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.DogService;
import org.woofenterprise.dogs.service.utils.BaseTestCase;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class DogFacadeImplTest extends BaseTestCase {

    @Mock
    private DogService dogService;
    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private DogFacadeImpl dogFacade;

    private Mapper mapper;

    @Before
    public void initDozer() {
        mapper = new DozerBeanMapper();
    }

    @Test
    public void testCreate() {
        Dog dog = mock(Dog.class);
        DogDTO dogDTO = mock(DogDTO.class);
        when(beanMappingService.map(dogDTO, Dog.class)).thenReturn(dog);
        dogFacade.createDog(dogDTO);
        verify(dogService).createDog(dog);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        DogDTO returnedDog = dogFacade.findDogById(id);
        verify(dogService).findDogById(id);
    }


    @Test
    public void testDelete() {
        DogDTO dogDTO = mock(DogDTO.class);
        Dog dog = mock(Dog.class);
        when(beanMappingService.map(dogDTO, Dog.class)).thenReturn(dog);
        dogFacade.deleteDog(dogDTO);
        verify(dogService).deleteDog(dog);
    }

    @Test
    public void testGetAll() {
        dogFacade.getAllDogs();
        verify(dogService).getAllDogs();
    }
}
