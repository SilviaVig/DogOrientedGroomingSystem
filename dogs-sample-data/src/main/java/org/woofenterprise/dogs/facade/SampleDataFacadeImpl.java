package org.woofenterprise.dogs.facade;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.service.AppointmentService;
import org.woofenterprise.dogs.service.CustomerService;
import org.woofenterprise.dogs.service.DogService;

@Component
@Transactional
public class SampleDataFacadeImpl implements SampleDataFacade {
    
    @Inject
    CustomerService customerService;
    
    @Inject
    AppointmentService appointmentService;

    @Inject
    DogService dogService;

    @Inject
    PasswordEncoder passwordEncoder;
    
    @Override
    public void createSampleData() {
        
        Customer admin = EntitiesFactory.createAdmin();
        admin.setPasswordHash(passwordEncoder.encode("admin"));
        customerService.createCustomer(admin);
        
        Customer user = EntitiesFactory.createCustomer(0);
        user.setEmail("user@user.cz");
        user.setPasswordHash(passwordEncoder.encode("user"));
        customerService.createCustomer(user);

        for (long seed = 20; seed <100; seed+=10) {
            
            Customer customer1 = EntitiesFactory.createCustomer(seed);
            customer1.setPasswordHash(passwordEncoder.encode("user"));
            customerService.createCustomer(customer1);

            Dog dog11 = EntitiesFactory.createDog(seed+1);
            Dog dog12 = EntitiesFactory.createDog(seed+2);
            customer1.addDog(dog11);
            customer1.addDog(dog12);
            dogService.createDog(dog11);
            dogService.createDog(dog12);

            Appointment appointment1 = EntitiesFactory.createAppointment(seed+3);
            appointment1.setDog(dog11);
            appointmentService.createAppointment(appointment1);
        
        }
    }
    
}
