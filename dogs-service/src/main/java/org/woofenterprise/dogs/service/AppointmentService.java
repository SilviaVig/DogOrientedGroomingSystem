package org.woofenterprise.dogs.service;

import org.woofenterprise.dogs.entity.Appointment;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentService {
    
    Appointment findAppointmentById(Long appointment);

    void createAppointment(Appointment appointment);

    void cancelAppointment(Appointment appointment);

    Collection<Appointment> getAllAppointments();

    //TODO: pridat metody z DAO
}
