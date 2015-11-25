package org.woofenterprise.dogs.dao;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woofenterprise.dogs.DogsPersistenceApplication;
import org.woofenterprise.dogs.entity.Appointment;

import javax.inject.Inject;
import org.apache.derby.impl.sql.execute.CreateConstraintConstantAction;

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
@SpringApplicationConfiguration(classes = DogsPersistenceApplication.class)
@Transactional
public class AppointmentDAOTest {

    private Customer exampleCustomer;
    private Customer exampleCustomer2;
    private Dog exampleDog;
    private Dog exampleDog2;
    
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
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        assertNotNull(appointment.getId());        
    }
    
    @Test
    public void createWithNullDog() {
        Appointment appointment = createAppointment();
        appointment.setCustomer(exampleCustomer);
        thrown.expect(DataIntegrityViolationException.class);
        appointmentDAO.create(appointment);
    }
    
    @Test
    public void findAll() {
        Appointment appointment = createAppointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = createAppointment(1);
        appointment2.setCustomer(exampleCustomer2);
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
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment result = appointmentDAO.findById(appointment.getId());
        assertNotNull(result);
        assertEquals(appointment, result);
    }
    
    @Test
    public void update() {
        Appointment appointment = createAppointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = createAppointment(1);
        appointment2.setDog(exampleDog2);
        appointment2.setCustomer(exampleCustomer2);
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
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = createAppointment(1);
        appointment2.setDog(exampleDog2);
        appointment2.setCustomer(exampleCustomer);
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
}
