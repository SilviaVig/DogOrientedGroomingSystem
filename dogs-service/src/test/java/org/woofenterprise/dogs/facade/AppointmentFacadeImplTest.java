package org.woofenterprise.dogs.facade;

import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprice.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.service.AppointmentDurationService;
import org.woofenterprise.dogs.service.AppointmentService;
import org.woofenterprise.dogs.service.utils.BaseTestCase;
import static org.woofenterprise.dogs.service.utils.EntitiesFactory.createAppointment;
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

    @InjectMocks
    private AppointmentFacadeImpl appointmentFacade;

    private Mapper mapper;

    @Before
    public void initDozer() {
        mapper = new DozerBeanMapper();
    }

    @Test
    public void testCreate() {
        Appointment appointment = createAppointment();
        AppointmentDTO appointmentDTO = mapper.map(appointment, AppointmentDTO.class);
        Long resultId = appointmentFacade.createAppointment(appointmentDTO);
        verify(appointmentService).createAppointment(appointment);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        AppointmentDTO returnedAppointment = appointmentFacade.findAppointmentById(id);
        verify(appointmentService).findAppointmentById(id);
    }

    //TODO: test na today
    
    @Test
    public void testCancel() {
        Long id = 5L;
        appointmentFacade.cancelAppointment(id);
        verify(appointmentService).cancelAppointment(id);
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
        Long duration = appointmentFacade.calculateAppointmentDuration(appointmentDTO);        
        assertEquals(expected, duration);
    }
}
