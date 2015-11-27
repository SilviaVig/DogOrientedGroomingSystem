package org.woofenterprise.dogs.facade;

import org.woofenterprice.dogs.dto.AppointmentDTO;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentFacade {

    /**
     *
     * @param appointment
     * @return
     */
    AppointmentDTO findAppointmentById(Long appointment);

    /**
     *
     * @return
     */
    Collection<AppointmentDTO> findAllAppointmentsForToday();

    /**
     *
     * @param appointmentDTO
     */
    void createAppointment(AppointmentDTO appointmentDTO);

    /**
     *
     * @param appointmentDTO
     */
    void cancelAppointment(AppointmentDTO appointmentDTO);

    /**
     *
     * @return
     */
    Collection<AppointmentDTO> getAllAppointments();
    
    /**
     *
     * @param appointmentDTO
     * @return
     */
    Long calculateAppointmentDuration(AppointmentDTO appointmentDTO);
}
