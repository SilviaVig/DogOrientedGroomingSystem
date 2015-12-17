package org.woofenterprise.dogs.facade;

import java.util.Collection;
import javax.inject.Inject;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.DogService;


public class DogFacadeImpl implements DogFacade {

    @Inject
    BeanMappingService beanMappingService;
    
    @Inject
    DogService dogService;
    
    @Override
    public DogDTO findDogById(Long dogId) {
        Dog d = dogService.findDogById(dogId);
        return beanMappingService.map(d, DogDTO.class);
    }

    @Override
    public void createDog(DogDTO dogDTO) {
        Dog d = beanMappingService.map(dogDTO, Dog.class);
        dogService.createDog(d);
    }

    @Override
    public void deleteDog(DogDTO dogDTO) {
        Dog d = beanMappingService.map(dogDTO, Dog.class);
        dogService.deleteDog(d);
    }

    @Override
    public Collection<DogDTO> getAllDogs() {
        Collection<Dog> dogs = dogService.getAllDogs();
        return beanMappingService.map(dogs, DogDTO.class);
    }

}
