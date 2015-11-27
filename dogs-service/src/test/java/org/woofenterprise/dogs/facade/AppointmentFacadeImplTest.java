package org.woofenterprise.dogs.facade;

import java.util.Date;
import java.util.Collection;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprice.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.service.AppointmentDurationService;
import org.woofenterprise.dogs.service.AppointmentService;
import org.woofenterprise.dogs.service.DateService;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.utils.BaseTestCase;
import org.woofenterprise.dogs.utils.Procedure;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class AppointmentFacadeImplTest extends BaseTestCase {

    @Mock
    private AppointmentService appointmentService;
    
    @Mock
    private AppointmentDurationService durationService;
    
    @Mock
    private DateService dateService;
    
    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private AppointmentFacadeImpl appointmentFacade;

    private Mapper mapper;
    private long day = 24 * 60 * 60;

    @Before
    public void initDozer() {
        mapper = new DozerBeanMapper();
    }

    @Test
    public void testCreate() {
        AppointmentDTO appointmentDTO = mock(AppointmentDTO.class);
        when(beanMappingService.map(appointmentDTO, Appointment.class)).thenReturn(appointment);
        appointmentFacade.createAppointment(appointmentDTO);
        verify(appointmentService).createAppointment(appointment);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        AppointmentDTO returnedAppointment = appointmentFacade.findAppointmentById(id);
        verify(appointmentService).findAppointmentById(id);
    }

    @Test 
    public void testFindAppointmentsForToday() {
        Date today = mock(Date.class);
        when(dateService.getToday()).thenReturn(today);
        appointmentFacade.findAllAppointmentsForToday();
        verify(appointmentService).getAllAppointmentsForRange(eq(today), any(Date.class));
    }
    
    @Test
    public void testCancel() {
        Appointment appointment = mock(Appointment.class);
        AppointmentDTO appointmentDTO = mock(AppointmentDTO.class);
        when(beanMappingService.map(appointmentDTO, Appointment.class)).thenReturn(appointment);
        appointmentFacade.cancelAppointment(appointmentDTO);
        verify(appointmentService).cancelAppointment(appointment);
    }

    @Test
    public void testGetAll() {
        appointmentFacade.getAllAppointments();
        verify(appointmentService).getAllAppointments();
    }

    @Test
    public void testCalculateDuration() {
        Appointment appointment = createAppointment();
        AppointmentDTO appointmentDTO = mapper.map(appointment, AppointmentDTO.class);
        
        Long expected = 50L;
        
        when( durationService.getDurationForProcedures( (List<Procedure>) any() ) ).thenReturn(expected);        
        when( durationService.getDurationForProcedures( (Collection<Procedure>) any() ) ).thenReturn(expected);        
        Long duration = appointmentFacade.calculateAppointmentDuration(appointmentDTO);        
        assertEquals(expected, duration);
    }
}
