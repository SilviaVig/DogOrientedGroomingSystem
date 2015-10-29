package org.woofenterprise.dogs.dao;

import java.util.List;
import org.woofenterprise.dogs.entity.Dog;

/**
 * DAO for {@link Dog} entity class.
 *
 * @author michal.babel@embedit.cz
 */
public interface DogDAO {

    public Dog findById(Long id);

    public void create(Dog d);
    
    public void update(Dog d);

    public void delete(Dog d);

    public List<Dog> findAll();
}
