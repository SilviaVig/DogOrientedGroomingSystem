package org.woofenterprise.dogs.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.woofenterprise.dogs.entity.Appointment;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

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

    @Override
    public List<Appointment> findAllAppointmentsForRange(Date startTime, Date endTime) {
        return em.createQuery("select a from Appointment a where a.startTime >= :startTime AND a.startTime <:endTime", Appointment.class)
                .setParameter("startTime", startTime, TemporalType.TIMESTAMP)
                .setParameter("endTime", endTime, TemporalType.TIMESTAMP)
                .getResultList();
    }
    
    @Override
    public List<Appointment> findAllAppointmentsAfter(Date time) {
        return em.createQuery("select a from Appointment a where a.startTime >= :time", Appointment.class)
                .setParameter("time", time, TemporalType.TIMESTAMP)
                .getResultList();
    }
    
    @Override
    public List<Appointment> findAllAppointmentsBefore(Date time) {
        return em.createQuery("select a from Appointment a where a.startTime <= :time", Appointment.class)
                .setParameter("time", time, TemporalType.TIMESTAMP)
                .getResultList();
    }
}
