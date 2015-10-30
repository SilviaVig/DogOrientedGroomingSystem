package org.woofenterprise.dogs.dao;

import org.woofenterprise.dogs.entity.Appointment;

import java.util.List;

/**
 * DAO for {@link org.woofenterprise.dogs.entity.Appointment} entity
 */
public interface AppointmentDAO {

    /**
     * Returns appointment with specified id.
     *
     * @param id appointments id
     * @return appointment entity with specified id
     */
    public Appointment findById(Long id);

    /**
     * Persists appointment entity.
     *
     * @param appointment appointment to persist
     */
    public void create(Appointment appointment);

    /**
     * Removes a persisted appointment.
     *
     * @param appointment appointment to delete
     */
    public void delete(Appointment appointment);
    
    /**
     * Updates a persisted appointment.
     *
     * @param appointment appointment to update
     */
    public void update(Appointment appointment);

    /**
     * Retrieves all persisted appointments.
     *
     * @return list of all persisted appointments
     */
    public List<Appointment> findAll();
}
