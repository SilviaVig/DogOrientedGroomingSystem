package org.woofenterprise.dogs.facade;

import org.woofenterprice.dogs.dto.AddProcedureDTO;
import org.woofenterprice.dogs.dto.AppointmentDTO;

import java.util.Collection;

/**
 * Created by Silvia.Vigasova on 25.11.2015.
 */
public interface AppointmentFacade {
    AppointmentDTO findAppointmentById(Long appointment);

    Collection<AppointmentDTO> findAllAppointmentsForToday();

    Long createAppointment(AppointmentDTO appointmentDTO);

    void cancelAppointment(Long appointmentId);

    Collection<AppointmentDTO> getAllAppointments();
    
}
