package org.woofenterprise.dogs.dao;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woofenterprise.dogs.config.PersistenceConfiguration;
import org.woofenterprise.dogs.entity.Appointment;

import javax.inject.Inject;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.utils.Procedure;

import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createAppointment;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createDog;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createCustomer;
/**
 * Test class for AppointmentDAO
 * 
 * @author michal.babel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class AppointmentDAOTest {

    private Customer exampleCustomer;
    private Customer exampleCustomer2;
    private Dog exampleDog;
    private Dog exampleDog2;
    private long today;
    private long tomorrow;
    private long yesterday;

    @Inject
    private CustomerDAO customerDAO;
    @Inject
    private DogDAO dogDAO;
    @Inject
    private AppointmentDAO appointmentDAO;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
        
    @Before
    public void createEntities() {
        today = 1 * 365 * 24 * 60 * 60;
        tomorrow = 60 * 60 * 24 + today;
        yesterday = today - 60 * 60 * 24 ;
        
        exampleCustomer = createCustomer();
        customerDAO.create(exampleCustomer);
        
        exampleCustomer2 = createCustomer(1);
        customerDAO.create(exampleCustomer2);
        
        exampleDog = createDog();
        exampleDog.setOwner(exampleCustomer);
        dogDAO.create(exampleDog);
        
        exampleDog2 = createDog(1);
        exampleDog2.setOwner(exampleCustomer2);
        dogDAO.create(exampleDog2);
    }

    
    @Test
    public void create() {
        Appointment appointment = createAppointment();
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        assertNotNull(appointment.getId());        
    }
    
    @Test
    public void createWithNullDog() {
        Appointment appointment = createAppointment();
        thrown.expect(DataIntegrityViolationException.class);
        appointmentDAO.create(appointment);
    }
    
    @Test
    public void findAll() {
        Appointment appointment = createAppointment();
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = createAppointment(1);
        appointment2.setDog(exampleDog2);
        appointmentDAO.create(appointment2);
        
        List<Appointment> result = appointmentDAO.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(appointment));
        assertTrue(result.contains(appointment2));
    }

    @Test
    public void findById() {
        Appointment appointment = createAppointment();
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment result = appointmentDAO.findById(appointment.getId());
        assertNotNull(result);
        assertEquals(appointment, result);
    }
    
    @Test
    public void update() {
        Appointment appointment = createAppointment();
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = createAppointment(1);
        appointment2.setDog(exampleDog2);
        appointmentDAO.create(appointment2);
        
        appointment.addProcedure(Procedure.WASHING);
        appointmentDAO.update(appointment);
       
        Appointment result = appointmentDAO.findById(appointment.getId());
        assertTrue(result.getProcedures().contains(Procedure.WASHING));
        Appointment result2 = appointmentDAO.findById(appointment2.getId());
        assertEquals(appointment2, result2);      
    }

    @Test
    public void delete() {
        Appointment appointment = createAppointment();
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = createAppointment(1);
        appointment2.setDog(exampleDog2);
        appointmentDAO.create(appointment2);
        
        List<Appointment> result = appointmentDAO.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(appointment));
        assertTrue(result.contains(appointment2));
       
        appointmentDAO.delete(appointment);
        
        List<Appointment> result2 = appointmentDAO.findAll();
        assertEquals(1, result2.size());
        assertFalse(result2.contains(appointment));
        assertTrue(result2.contains(appointment2));
    }
    
    @Test
    public void getAppointmentsForRange() {
        Date start1 = new Date(today);
        Date end1 = new Date(today + 60 * 60 * 3);
        Date start2 = new Date(today + 60 * 60 * 2);
        Date end2 = new Date(today + 60 * 60 * 5);
        Date start3 = new Date(tomorrow);
        Date end3 = new Date(tomorrow + 60 * 60 * 3);
        Date start4 = new Date(yesterday);
        Date end4 = new Date(yesterday + 60 * 60 * 5);

        Appointment appointment1 = new Appointment();
        appointment1.setDog(exampleDog);
        appointment1.setStartTime(start1);
        appointment1.setEndTime(end1);
        appointmentDAO.create(appointment1);
        
        Appointment appointment2 = new Appointment();
        appointment2.setDog(exampleDog);
        appointment2.setStartTime(start2);
        appointment2.setEndTime(end2);
        appointmentDAO.create(appointment2);
        
        Appointment appointment3 = new Appointment();
        appointment3.setDog(exampleDog);
        appointment3.setStartTime(start3);
        appointment3.setEndTime(end3);
        appointmentDAO.create(appointment3);
        
        Appointment appointment4 = new Appointment();
        appointment4.setDog(exampleDog);
        appointment4.setStartTime(start4);
        appointment4.setEndTime(end4);
        appointmentDAO.create(appointment4);
        
        assertEquals(2, appointmentDAO.findAllAppointmentsForRange(start1, end2).size());
        assertEquals(1, appointmentDAO.findAllAppointmentsAfter(start3).size());
        assertEquals(1, appointmentDAO.findAllAppointmentsBefore(start4).size());
    }
}
