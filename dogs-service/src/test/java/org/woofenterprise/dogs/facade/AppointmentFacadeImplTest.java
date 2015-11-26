package org.woofenterprise.dogs.facade;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.woofenterprice.dogs.dto.AppointmentDTO;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.service.AppointmentService;
import org.woofenterprise.dogs.service.utils.BaseTestCase;
import static org.woofenterprise.dogs.service.utils.EntitiesFactory.createAppointment;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class AppointmentFacadeImplTest extends BaseTestCase {

    @Mock
    private AppointmentService appointmentService;

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

}
