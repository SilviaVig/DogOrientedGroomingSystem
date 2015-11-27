package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Dog;

import java.util.Collection;

/**
 * An interface that defines a service access to the {@link Dog} entity.
 * 
 * @author Silvia.Vigasova 
 */
public interface DogService {

    /**
     * Find dog with specific id.
     * @param dogId id of dog to find
     * @return Dog entity with specific nid
     */
    Dog findDogById(Long dogId);

    /**
     * Creates Dog.
     * @param dog dog to  create
     */
    void createDog(Dog dog);

    /**
     * Deletes dog.
     * @param dog dog to delete
     */
    void deleteDog(Dog dog);

    /**
     * Retrieves all dogs.
     * @return all dogs
     */
    Collection<Dog> getAllDogs();
    
}
