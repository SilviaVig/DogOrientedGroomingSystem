package org.woofenterprise.dogs.facade;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.service.AppointmentDurationService;
import org.woofenterprise.dogs.service.AppointmentService;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.DateService;

@Service
@Transactional
public class AppointmentFacadeImpl implements AppointmentFacade {
    
    @Inject
    AppointmentService appointmentService;
    
    @Inject
    DateService dateService;
    
    @Inject 
    AppointmentDurationService durationService;
    
    @Inject
    BeanMappingService beanMappingService;

    @Override
    public AppointmentDTO findAppointmentById(Long appointment) {
        Appointment a = appointmentService.findAppointmentById(appointment);
        return beanMappingService.map(a, AppointmentDTO.class);
    }

    @Override
    public Collection<AppointmentDTO> findAllAppointmentsForToday() {
        Date today = dateService.getToday();
        Calendar c = Calendar.getInstance(); 
        c.setTime(today); 
        c.add(Calendar.DATE, 1);
        Date tommorow = c.getTime();
        Collection<Appointment> appointments = appointmentService.getAllAppointmentsForRange(today, tommorow);
        return beanMappingService.map(appointments, AppointmentDTO.class);
    }

    @Override
    public void createAppointment(AppointmentDTO appointmentDTO) {
        Appointment a = beanMappingService.map(appointmentDTO, Appointment.class);
        appointmentService.createAppointment(a);
    }

    @Override
    public void cancelAppointment(AppointmentDTO appointmentDTO) {
        Appointment a = beanMappingService.map(appointmentDTO, Appointment.class);
        appointmentService.cancelAppointment(a);
    }

    @Override
    public Collection<AppointmentDTO> getAllAppointments() {
        Collection<Appointment> appointments = appointmentService.getAllAppointments();
        return beanMappingService.map(appointments, AppointmentDTO.class);
    }

    @Override
    public Long calculateAppointmentDuration(AppointmentDTO appointmentDTO) {
        return durationService.getDurationForProcedures(appointmentDTO.getProcedures());
    }

}
