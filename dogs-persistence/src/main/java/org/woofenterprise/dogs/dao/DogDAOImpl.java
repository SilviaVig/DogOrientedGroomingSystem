package org.woofenterprise.dogs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Dog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DogDAOImpl implements DogDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Dog findById(Long id) {
        return em.find(Dog.class, id);
    }

    @Override
    public void create(Dog d) {
        em.persist(d);
    }

    @Override
    public void delete(Dog d) {
        d = em.merge(d);
        em.remove(d);
    }

    @Override
    public List<Dog> findAll() {
        return em.createQuery("select d from Dog d", Dog.class).getResultList();
    }

    @Override
    public void update(Dog d) {
        em.merge(d);
    }

}
