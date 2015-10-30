package org.woofenterprise.dogs.dao;

import java.util.List;
import org.woofenterprise.dogs.entity.Dog;

/**
 * DAO for {@link Dog} entity class.
 *
 * @author michal.babel@embedit.cz
 */
public interface DogDAO {

    /**
     * Returns dog with specified id.
     *
     * @param id dogs id
     * @return dog entity with specified id
     */
    public Dog findById(Long id);

    /**
     * Persists dog entity.
     *
     * @param d dog to persist
     */
    public void create(Dog d);
    

    /**
     * Updates a persisted dog.
     *
     * @param d dog to update
     */
    public void update(Dog d);

    /**
     * Removes a persisted dog.
     *
     * @param d dog to delete
     */
    public void delete(Dog d);

    /**
     * Retrieves all persisted dogs.
     *
     * @return list of all persisted dogs
     */
    public List<Dog> findAll();
}
