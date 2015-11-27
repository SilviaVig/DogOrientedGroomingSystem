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
    void createDog(Dog dog);

    /**
     *
     * @param dog
     */
    void deleteDog(Dog dog);

    /**
     *
     * @return
     */
    Collection<Dog> getAllDogs();
    
}
