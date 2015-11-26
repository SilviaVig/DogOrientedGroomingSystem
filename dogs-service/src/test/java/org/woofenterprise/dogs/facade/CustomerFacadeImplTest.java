package org.woofenterprise.dogs.facade;

import javax.inject.Inject;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.woofenterprice.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.service.CustomerService;
import static org.woofenterprise.dogs.service.utils.EntitiesFactory.createCustomer;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class CustomerFacadeImplTest {
    
    @Mock
    CustomerService customerService;
    
    @Inject
    @InjectMocks
    CustomerFacade customerFacade;
    
    @Rule
    ExpectedException thrown = ExpectedException.none();
    
    Mapper mapper;
    
    @Before
    public void initDozer() {
        mapper = new DozerBeanMapper();
    }
    
    @Test
    public void testCreate() {
        Customer customer = createCustomer();
        CustomerDTO customerDTO = mapper.map(customer, CustomerDTO.class);    
        Long resultId = customerFacade.createCustomer(customerDTO);
        verify(customerService).createCustomer(customer);
    }
    
    @Test
    public void testFindById() {
        Long id = 5L;
        CustomerDTO returnedCustomer = customerFacade.findCustomerById(id);
        verify(customerService).findCustomerById(id);
    }
    
    @Test
    public void testFindByEmail() {
        String email = "customer@example.com";
        CustomerDTO returnedCustomer = customerFacade.findCustomerByEmail(email);
        verify(customerService).findCustomerByEmail(email);
    }
    
    @Test
    public void testDelete() {
        Long id = 5L;
        customerFacade.deleteCustomer(id);
        verify(customerService).deleteCustomer(id);
    }
    
    @Test
    public void testGetAll() {
        customerFacade.getAllCustomers();
        verify(customerService).getAllCustomers();
    }
}
