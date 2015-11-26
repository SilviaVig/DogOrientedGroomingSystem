package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Appointment;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentService {
    
    Appointment findAppointmentById(Long appointment);

    Collection<Appointment> getAllAppointmentsForToday();

    Long createAppointment(Appointment appointmentDTO);

    void cancelAppointment(Long appointmentId);

    Collection<Appointment> getAllAppointments();

}
