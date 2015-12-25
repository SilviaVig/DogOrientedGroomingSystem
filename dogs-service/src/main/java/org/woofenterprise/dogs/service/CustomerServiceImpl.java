package org.woofenterprise.dogs.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.woofenterprise.dogs.dao.CustomerDAO;
import org.woofenterprise.dogs.entity.Customer;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;

/**
 * @author Silvia Vigasova
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerDAO.findById(customerId);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDAO.create(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDAO.delete(customer);
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public Customer changeEmail(Long customerId, String email) {
        Customer customer = customerDAO.findById(customerId);
        customer.setEmail(email);
        return customerDAO.update(customer);
    }
    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public boolean authenticate(Customer c, String password) {

        return validatePassword(password, c.getPasswordHash());
    }

    @Override
    public boolean isAdmin(Customer customer) {
        //must get a fresh copy from database
        return findCustomerById(customer.getId()).isAdmin();
    }

    private static boolean validatePassword(String password, String correctHash) {
        if (password == null) return false;
        if (correctHash == null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
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

}
