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

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.utils.Procedure;

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
        exampleCustomer = new Customer();
        exampleCustomer.setName("John");
        exampleCustomer.setSurname("Doe");        
        exampleCustomer.setAddressCity("city");
        exampleCustomer.setAddressPostalCode("code");
        exampleCustomer.setAddressCountry("country");
        exampleCustomer.setAddressFirstLine("first line");
        exampleCustomer.setAddressSecondLine("Second line");
        customerDAO.create(exampleCustomer);
        
        exampleCustomer2 = new Customer();
        exampleCustomer2.setName("Jane");
        exampleCustomer2.setSurname("Doe");
        exampleCustomer2.setAddressCity("city");
        exampleCustomer2.setAddressPostalCode("code");
        exampleCustomer2.setAddressCountry("country");
        exampleCustomer2.setAddressFirstLine("first line");
        exampleCustomer2.setAddressSecondLine("Second line");
        customerDAO.create(exampleCustomer2);
        
        exampleDog = new Dog();
        exampleDog.setName("Woofie");
        exampleDog.setHobbies("barking");
        exampleDog.setOwner(exampleCustomer);
        dogDAO.create(exampleDog);
        
        exampleDog2 = new Dog();
        exampleDog2.setName("Fifi");
        exampleDog2.setHobbies("barking");
        exampleDog2.setOwner(exampleCustomer2);
        dogDAO.create(exampleDog2);
    }

    
    @Test
    public void create() {
        Appointment appointment = new Appointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 10, 0);
        endTime.set(2015, 0, 1, 12, 0);
        appointment.setStartTime(startTime.getTime());
        appointment.setEndTime(endTime.getTime());
        appointment.addProcedure(Procedure.BRUSHING);
        appointment.addProcedure(Procedure.CLAWS_CUTTING);
        appointmentDAO.create(appointment);
        assertNotNull(appointment.getId());        
    }
    
    @Test
    public void createWithNullDog() {
        Appointment appointment = new Appointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(null);
        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 10, 0);
        endTime.set(2015, 0, 1, 12, 0);
        appointment.setStartTime(startTime.getTime());
        appointment.setEndTime(endTime.getTime());
        appointment.addProcedure(Procedure.BRUSHING);
        appointment.addProcedure(Procedure.CLAWS_CUTTING);
        
        thrown.expect(DataIntegrityViolationException.class);
        appointmentDAO.create(appointment);
    }
    
    @Test
    public void findAll() {
        Appointment appointment = new Appointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 10, 0);
        endTime.set(2015, 0, 1, 12, 0);
        appointment.setStartTime(startTime.getTime());
        appointment.setEndTime(endTime.getTime());
        appointment.addProcedure(Procedure.BRUSHING);
        appointment.addProcedure(Procedure.CLAWS_CUTTING);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = new Appointment();
        appointment2.setCustomer(exampleCustomer2);
        appointment2.setDog(exampleDog2);
        Calendar startTime2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 12, 0);
        endTime.set(2015, 0, 1, 14, 0);
        appointment2.setStartTime(startTime2.getTime());
        appointment2.setEndTime(endTime2.getTime());
        appointment2.addProcedure(Procedure.BRUSHING);
        appointment2.addProcedure(Procedure.CLAWS_CUTTING);
        appointmentDAO.create(appointment2);
        
        List<Appointment> result = appointmentDAO.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(appointment));
        assertTrue(result.contains(appointment2));
    }

    @Test
    public void findById() {
        Appointment appointment = new Appointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 10, 0);
        endTime.set(2015, 0, 1, 12, 0);
        appointment.setStartTime(startTime.getTime());
        appointment.setEndTime(endTime.getTime());
        appointment.addProcedure(Procedure.BRUSHING);
        appointment.addProcedure(Procedure.CLAWS_CUTTING);
        appointmentDAO.create(appointment);
        
        Appointment result = appointmentDAO.findById(appointment.getId());
        assertNotNull(result);
        assertEquals(appointment, result);
    }
    
    @Test
    public void update() {
        Appointment appointment = new Appointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 10, 0);
        endTime.set(2015, 0, 1, 12, 0);
        appointment.setStartTime(startTime.getTime());
        appointment.setEndTime(endTime.getTime());
        appointment.addProcedure(Procedure.BRUSHING);
        appointment.addProcedure(Procedure.CLAWS_CUTTING);
        appointment.setCustomer(exampleCustomer);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = new Appointment();
        appointment2.setDog(exampleDog2);
        Calendar startTime2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 12, 0);
        endTime.set(2015, 0, 1, 14, 0);
        appointment2.setStartTime(startTime2.getTime());
        appointment2.setEndTime(endTime2.getTime());
        appointment2.addProcedure(Procedure.BRUSHING);
        appointment2.addProcedure(Procedure.CLAWS_CUTTING);
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
        Appointment appointment = new Appointment();
        appointment.setCustomer(exampleCustomer);
        appointment.setDog(exampleDog);
        Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 10, 0);
        endTime.set(2015, 0, 1, 12, 0);
        appointment.setStartTime(startTime.getTime());
        appointment.setEndTime(endTime.getTime());
        appointment.addProcedure(Procedure.BRUSHING);
        appointment.addProcedure(Procedure.CLAWS_CUTTING);
        appointment.setCustomer(exampleCustomer2);
        appointmentDAO.create(appointment);
        
        Appointment appointment2 = new Appointment();
        appointment2.setDog(exampleDog2);
        Calendar startTime2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar endTime2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        startTime.set(2015, 0, 1, 12, 0);
        endTime.set(2015, 0, 1, 14, 0);
        appointment2.setCustomer(exampleCustomer);
        appointment2.setStartTime(startTime2.getTime());
        appointment2.setEndTime(endTime2.getTime());
        appointment2.addProcedure(Procedure.BRUSHING);
        appointment2.addProcedure(Procedure.CLAWS_CUTTING);
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
