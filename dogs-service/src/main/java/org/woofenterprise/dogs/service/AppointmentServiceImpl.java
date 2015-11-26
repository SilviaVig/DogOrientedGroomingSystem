package org.woofenterprise.dogs.service;

import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.entity.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Inject
    DateService dateService;
    
    @Override
    public Appointment findAppointmentById(Long appointment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Appointment> getAllAppointmentsForToday() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long createAppointment(Appointment appointmentDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Appointment> getAllAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
