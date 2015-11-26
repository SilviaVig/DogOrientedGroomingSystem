package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Dog;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface DogService {
    Dog findDogById(Long dogId);

    Long createDog(Dog dog);

    void deleteDog(Long dogId);

    Collection<Dog> getAllDogs();
    
}
