package org.woofenterprise.dogs.facade;

import java.util.Collection;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.dto.DogDTO;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.CustomerService;
import org.woofenterprise.dogs.service.DogService;

@Service
@Transactional
public class DogFacadeImpl implements DogFacade {

    @Inject
    BeanMappingService beanMappingService;
    
    @Inject
    DogService dogService;
    
    @Inject
    CustomerService customerService;
    
    @Override
    public DogDTO findDogById(Long dogId) {
        Dog d = dogService.findDogById(dogId);
        return (d == null) ? null : beanMappingService.map(d, DogDTO.class);
    }

    @Override
    public DogDTO createDog(DogDTO dogDTO) {
        Dog d = beanMappingService.map(dogDTO, Dog.class);
        dogService.createDog(d);
        return beanMappingService.map(d, DogDTO.class);
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

    @Override
    public void updateDog(DogDTO dogDTO) {
        dogService.updateDog(beanMappingService.map(dogDTO, Dog.class));
    }

}
