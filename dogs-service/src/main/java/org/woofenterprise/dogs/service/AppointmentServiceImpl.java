package org.woofenterprise.dogs.service;

import java.util.Collection;
import java.util.Date;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.dao.AppointmentDAO;
import org.woofenterprise.dogs.entity.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Inject
    DateService dateService;
    
    @Inject
    AppointmentDAO appointmentDAO;
    
    @Override
    public Appointment findAppointmentById(Long appointmentId) {
        return appointmentDAO.findById(appointmentId);
    }

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentDAO.create(appointment);
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
       appointmentDAO.delete(appointment);
    }

    @Override
    public Collection<Appointment> getAllAppointments() {
       return appointmentDAO.findAll();
    }

    @Override
    public Collection<Appointment> getAllAppointmentsForRange(Date startTime, Date endTime) {
       return appointmentDAO.findAllAppointmentsForRange(startTime, endTime);
    }

    @Override
    public Collection<Appointment> getAllAppointmentsBefore(Date time) {
        return appointmentDAO.findAllAppointmentsBefore(time);
    }

    @Override
    public Collection<Appointment> getAllAppointmentsAfter(Date time) {
        return appointmentDAO.findAllAppointmentsAfter(time);
    }

    @Override
    public void updateAppointment(Appointment map) {
        appointmentDAO.update(map);
    }

}
