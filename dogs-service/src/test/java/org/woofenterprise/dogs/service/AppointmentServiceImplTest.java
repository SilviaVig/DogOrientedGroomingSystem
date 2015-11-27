package org.woofenterprise.dogs.service;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprise.dogs.dao.AppointmentDAO;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.service.utils.BaseTestCase;
import static org.woofenterprise.dogs.service.utils.EntitiesFactory.*;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class AppointmentServiceImplTest extends BaseTestCase {

    @Mock
    private DateService dateService;
    @Mock
    private AppointmentDAO appointmentDAO;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    public void testCreate() {
        Appointment a = createAppointment();
        appointmentService.createAppointment(a);
        verify(appointmentDAO).create(a);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        Appointment returnedAppointment = appointmentService.findAppointmentById(id);
        verify(appointmentDAO).findById(id);
    }

    @Test
    public void testCancel() {
        Long id = 5L;
        Appointment appointment = createAppointment();
        appointment.setId(id);
        when(appointmentDAO.findById(id)).thenReturn(appointment);
        appointmentService.cancelAppointment(appointment);
        verify(appointmentDAO).findById(id);
        verify(appointmentDAO).delete(appointment);
    }

    @Test
    public void testGetAll() {
        Collection<Appointment> result = appointmentService.getAllAppointments();
        verify(appointmentDAO).findAll();
    }
    
    @Test
    public void testGetAppointmentsForRange() {
        Date time = dateService.getToday();
        Collection<Appointment> result = appointmentService.getAllAppointmentsForRange(time, time);
        verify(appointmentDAO).findAllAppointmentsForRange(time, time);
    } 
    @Test
    public void testGetAppointmentsAfter() {
        Date time = dateService.getToday();
        Collection<Appointment> result = appointmentService.getAllAppointmentsAfter(time);
        verify(appointmentDAO).findAllAppointmentsAfter(time);
    }
    @Test
    public void testGetAppointmentsBefore() {
        Date time = dateService.getToday();
        Collection<Appointment> result = appointmentService.getAllAppointmentsBefore(time);
        verify(appointmentDAO).findAllAppointmentsBefore(time);
    }
}
