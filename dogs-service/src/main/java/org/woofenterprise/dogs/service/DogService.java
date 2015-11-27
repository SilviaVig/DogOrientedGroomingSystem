package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Dog;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface DogService {

    /**
     *
     * @param dogId
     * @return
     */
    Dog findDogById(Long dogId);

    /**
     *
     * @param dog
     * @return
     */
    Long createDog(Dog dog);

    /**
     *
     * @param dogId
     */
    void deleteDog(Long dogId);

    /**
     *
     * @return
     */
    Collection<Dog> getAllDogs();
    
}
