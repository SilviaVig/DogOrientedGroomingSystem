package org.woofenterprise.dogs.facade;

import org.woofenterprice.dogs.dto.AppointmentDTO;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentFacade {

    /**
     * Finds appointments by given id.
     *
     * @param appointmentId id of appointment to find
     * @return Appointment converted to DTO object
     */
    AppointmentDTO findAppointmentById(Long appointmentId);

    /**
     * Retrieves all appointments scheduled for today.
     *
     * @return all appointments scheduled for today converted to DTO objects
     */
    Collection<AppointmentDTO> findAllAppointmentsForToday();

    /**
     * Creates appointment entity from DTO object.
     *
     * @param appointmentDTO appointment to create
     */
    void createAppointment(AppointmentDTO appointmentDTO);

    /**
     * Cancels given appointment.
     *
     * @param appointmentDTO appointment to cancel.
     */
    void cancelAppointment(AppointmentDTO appointmentDTO);

    /**
     * Retrieves all appintments.
     *
     * @return all appointments converted to DTO objects
     */
    Collection<AppointmentDTO> getAllAppointments();

    /**
     * Calculates appointment duration.
     *
     * @param appointmentDTO appointment to be calculated
     * @return calculated duration
     */
    Long calculateAppointmentDuration(AppointmentDTO appointmentDTO);
}
