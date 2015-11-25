package org.woofenterprise.dogs.dao.utils;

import com.github.javafaker.Faker;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.utils.Procedure;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class EntitiesFactory {
    
    public static Customer createCustomer() {
        return createCustomer(0);
    }
    
    public static Dog createDog() {
        return createDog(0);
    }
    
    public static Appointment createAppointment() {
        return createAppointment(0);
    }
    
    public static Customer createCustomer(long seed) {
        Faker faker = new Faker(new Random(seed));
        Customer result = new Customer();
        result.setName(faker.name().firstName());
        result.setSurname(faker.name().lastName());
        result.setEmail(faker.internet().emailAddress());
        result.setAddressCity(faker.address().citySuffix());
        result.setAddressCountry(faker.address().country());
        result.setAddressFirstLine(faker.address().streetAddress(false));
        result.setAddressPostalCode(faker.address().zipCode());
        result.setPhoneNumber(faker.phoneNumber().phoneNumber());
        return result;
    }
    
    public static Dog createDog(long seed) {
        Faker faker = new Faker(new Random(seed));
        Dog result = new Dog();
        result.setName(faker.name().fullName());
        result.setHobbies(faker.lorem().sentence(4));
        return result;
    }
    
    public static Appointment createAppointment(long seed) {
        Faker faker = new Faker(new Random(seed));
        Appointment result = new Appointment();
        Date start = faker.date().future(3, TimeUnit.DAYS);
        Date end = faker.date().future(2, TimeUnit.HOURS, start);
        result.setStartTime(start);
        result.setEndTime(end);
        result.addProcedure(Procedure.BRUSHING);
        result.addProcedure(Procedure.CLAWS_CUTTING);
        return result;
    }
    
}
