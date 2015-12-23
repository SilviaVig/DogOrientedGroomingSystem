/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.dao;

import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.config.PersistenceConfiguration;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createCustomer;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createAppointment;
import static org.woofenterprise.dogs.dao.utils.EntitiesFactory.createDog;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;

/**
 *
 * @author michal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class CascadeTest {
    
    @Inject
    CustomerDAO customerDAO;
    @Inject
    AppointmentDAO appointmentDAO;
    @Inject
    DogDAO dogDAO;
    
    @Test
    public void deleteDogsAndAppointments() {
        
        assertTrue(appointmentDAO.findAll().isEmpty());
        assertTrue(dogDAO.findAll().isEmpty());
        
        Customer customer = createCustomer();
        customerDAO.create(customer);
        
        Dog d1 = createDog();
        customer.addDog(d1);
        Dog d2 = createDog(1);
        customer.addDog(d2);
        dogDAO.create(d1);
        dogDAO.create(d2);
        
        Appointment appointment = createAppointment();
        d2.addAppointment(appointment);
        appointmentDAO.create(appointment);
        
        assertFalse(appointmentDAO.findAll().isEmpty());
        assertFalse(dogDAO.findAll().isEmpty());
        
        customerDAO.delete(customer);
        
        assertTrue(dogDAO.findAll().isEmpty());
        assertTrue(appointmentDAO.findAll().isEmpty());
        
    }
}
