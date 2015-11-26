package org.woofenterprise.dogs.facade;

import java.util.Collection;
import org.springframework.stereotype.Service;
import org.woofenterprice.dogs.dto.AppointmentDTO;

@Service
public class AppointmentFacadeImpl implements AppointmentFacade {

    @Override
    public AppointmentDTO findAppointmentById(Long appointment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<AppointmentDTO> findAllAppointmentsForToday() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long createAppointment(AppointmentDTO appointmentDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<AppointmentDTO> getAllAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long calculateAppointmentDuration(AppointmentDTO appointmentDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
