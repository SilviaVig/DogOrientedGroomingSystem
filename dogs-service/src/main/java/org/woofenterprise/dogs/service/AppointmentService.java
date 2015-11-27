package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Appointment;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentService {
    
    /**
     *
     * @param appointment
     * @return
     */
    Appointment findAppointmentById(Long appointment);

    /**
     *
     * @param appointment
     */
    void createAppointment(Appointment appointment);

    /**
     *
     * @param appointment
     */
    void cancelAppointment(Appointment appointment);

    /**
     *
     * @return
     */
    Collection<Appointment> getAllAppointments();

    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    Collection<Appointment> getAllAppointmentsForRange(Date startTime, Date endTime);
    
    /**
     *
     * @param time
     * @return
     */
    Collection<Appointment> getAllAppointmentsBefore(Date time);
    
    /**
     *
     * @param time
     * @return
     */
    Collection<Appointment> getAllAppointmentsAfter(Date time);
}
