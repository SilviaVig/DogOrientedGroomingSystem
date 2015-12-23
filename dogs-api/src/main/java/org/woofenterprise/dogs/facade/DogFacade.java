package org.woofenterprise.dogs.facade;

import java.util.Collection;
import org.woofenterprise.dogs.dto.DogCreateDTO;
import org.woofenterprise.dogs.dto.DogDTO;

/**
 *
 * @author michal.babel@embedit.cz
 */
public interface DogFacade {
    
    /**
     * Finds Dog by id.
     *
     * @param dogId dog id
     * @return dog converted to DTO object
     */
    DogDTO findDogById(Long dogId);

    /**
     * Creates dog.
     *
     * @param dogDTO dog to create.
     */
    DogDTO createDog(DogCreateDTO dogDTO);

    /**
     * Deletes dog.
     *
     * @param dogDTO dog to delete.
     */
    void deleteDog(DogDTO dogDTO);

    /**
     * Retrieves all dogs.
     *
     * @return collection of dogs converted to DTO objects.
     */
    Collection<DogDTO> getAllDogs();
    
}
