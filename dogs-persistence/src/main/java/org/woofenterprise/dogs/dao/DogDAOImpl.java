package org.woofenterprise.dogs.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Dog;

@Repository
public class DogDAOImpl implements DogDAO {

    @Override
    public Dog findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Dog d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Dog d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Dog> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Dog d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
