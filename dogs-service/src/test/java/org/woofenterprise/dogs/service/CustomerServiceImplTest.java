package org.woofenterprise.dogs.service;

import java.util.Collection;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.woofenterprise.dogs.dao.CustomerDAO;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.service.utils.BaseTestCase;
import static org.woofenterprise.dogs.service.utils.EntitiesFactory.createCustomer;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class CustomerServiceImplTest extends BaseTestCase {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testCreate() {
        Customer c = createCustomer();
        Long resultId = customerService.createCustomer(c);
        verify(customerDAO).create(c);
    }

    @Test
    public void testFindById() {
        Long id = 5L;
        Customer returnedCustomer = customerService.findCustomerById(id);
        verify(customerDAO).findById(id);
    }

    @Test
    public void testFindByEmail() {
        String email = "customer@example.com";
        Customer returnedCustomer = customerService.findCustomerByEmail(email);
        verify(customerDAO).findByEmail(email);
    }

    @Test
    public void testDelete() {
        Long id = 5L;
        Customer customer = mock(Customer.class);
        customerService.deleteCustomer(customer);
        verify(customerDAO).delete(customer);
    }

    @Test
    public void testGetAll() {
        Collection<Customer> result = customerService.getAllCustomers();
        verify(customerDAO).findAll();
    }

}
