package org.woofenterprise.dogs.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.dao.DogDAO;
import org.woofenterprise.dogs.entity.Dog;

import javax.inject.Inject;

/**
 * @author Silvia.Vigasova 
 */
@Service
public class DogServiceImpl implements DogService {

    @Inject
    private DogDAO dogDAO;

    @Override
    public Dog findDogById(Long dogId) {
        return dogDAO.findById(dogId);
    }

    @Override
    public void createDog(Dog dog) {
        dogDAO.create(dog);
    }

    @Override
    public void deleteDog(Dog dog) {
        dogDAO.delete(dog);
    }

    @Override
    public Collection<Dog> getAllDogs() {
        return dogDAO.findAll();
    }
}
