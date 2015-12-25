package org.woofenterprise.dogs.facade;

import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.utils.Procedure;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author michal.babel@embedit.cz
 */
class EntitiesFactory {
    
    public static Customer createCustomer() {
        return createCustomer(0);
    }
    
    public static Dog createDog() {
        return createDog(0);
    }
    
    public static Appointment createAppointment() {
        return createAppointment(0);
    }

    public static Customer createAdmin() {
        Customer customer = createCustomer(0);
        customer.setAdmin(true);
        customer.setName("Admin");
        customer.setEmail("admin@admin.cz");
        customer.setPasswordHash(createHash("admin"));
        return customer;
    }
    
    public static Customer createCustomer(long seed) {
        Faker faker = new Faker(new Random(seed));
        Customer result = new Customer();
        result.setName(faker.name().firstName());
        result.setSurname(faker.name().lastName());
        result.setEmail(faker.internet().emailAddress());
        result.setAddressCity(faker.address().city());
        result.setAddressCountry(faker.address().country());
        result.setAddressFirstLine(faker.address().streetAddress(false));
        result.setAddressPostalCode(faker.address().zipCode());
        result.setPhoneNumber(faker.phoneNumber().phoneNumber());
        result.setPasswordHash(createHash("password"));
        result.setAdmin(false);
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
        Random random = new Random(seed);
        Faker faker = new Faker(random);
        Appointment result = new Appointment();
        Date start = faker.date().future(3, TimeUnit.DAYS);
        Date end = faker.date().future(2, TimeUnit.HOURS, start);
        result.setStartTime(start);
        result.setEndTime(end);
        for (Procedure p : Procedure.values()) {
            if (random.nextBoolean()) {
                result.addProcedure(p);
            }
        }
        return result;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }
    
}
