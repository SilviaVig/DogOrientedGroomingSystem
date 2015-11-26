package org.woofenterprise.dogs.service;

import java.util.Collection;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprise.dogs.dao.AppointmentDAO;
import org.woofenterprise.dogs.entity.Appointment;
import static org.woofenterprise.dogs.service.utils.EntitiesFactory.*;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class AppointmentServiceImplTest {
    
    @Mock 
    DateService dateService;
    @Mock 
    AppointmentDAO appointmentDAO;
    
    @Inject
    @InjectMocks
    AppointmentService appointmentService;
    
    @Rule
    ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testCreate() {
        Appointment a = createAppointment();    
        Long resultId = appointmentService.createAppointment(a);
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
        appointmentService.cancelAppointment(id);
        verify(appointmentDAO).findById(id);
        verify(appointmentDAO).delete(appointment);
    }
    
    @Test
    public void testGetAll() {
        Collection<Appointment> result = appointmentService.getAllAppointments();
        verify(appointmentDAO).findAll();
    }
}
