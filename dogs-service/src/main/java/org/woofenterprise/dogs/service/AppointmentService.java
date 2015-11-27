package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Appointment;

import java.util.Collection;
import java.util.Date;

/**
 * An interface that defines a service access to the {@link Appointment} entity.
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentService {
    
    /**
     * Find appointment with specific id.
     * @param appointmentId appointment id
     * @return Appointment entity with given id
     */
    Appointment findAppointmentById(Long appointmentId);

    /**
     * Creates appointment .
     * @param appointment appointment to creaate
     */
    void createAppointment(Appointment appointment);

    /**
     * Cancels given appointment.
     * @param appointment appointment to cancel
     */
    void cancelAppointment(Appointment appointment);

    /**
     * Retrieves all appointments.
     * @return all apointments
     */
    Collection<Appointment> getAllAppointments();

    /**
     * Retrieves all appointments with start time in specific range.
     * @param startTime starting border
     * @param endTime ending border
     * @return appointments with starting time between startTime and endTime
     */
    Collection<Appointment> getAllAppointmentsForRange(Date startTime, Date endTime);
    
    /**
     * Retrieves all appointments that starts before specific time.
     * @param time ending border
     * @return appointments with starting time before time.
     */
    Collection<Appointment> getAllAppointmentsBefore(Date time);
    
    /**
     * Retrieves all appointments that starts after specific time.
     * @param time starting border
     * @return appointments with starting time after time
     */
    Collection<Appointment> getAllAppointmentsAfter(Date time);
}
