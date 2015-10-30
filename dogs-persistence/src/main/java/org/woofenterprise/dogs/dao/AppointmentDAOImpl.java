package org.woofenterprise.dogs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Appointment;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Appointment findById(Long id) {
        return em.find(Appointment.class, id);
    }

    @Override
    public void create(Appointment appointment) {
        em.persist(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        em.remove(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        em.merge(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return em.createQuery("select a from Appointment a", Appointment.class).getResultList();
    }

}
