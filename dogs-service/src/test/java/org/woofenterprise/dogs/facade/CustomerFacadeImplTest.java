package org.woofenterprise.dogs.facade;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.CustomerService;
import org.woofenterprise.dogs.service.DogService;
import org.woofenterprise.dogs.service.utils.BaseTestCase;

/**
 *
 * @author michal.babel@embedit.cz
 */
public class CustomerFacadeImplTest extends BaseTestCase {

    @Mock
    private CustomerService customerService;
    @Mock
    private BeanMappingService beanMappingService;
    @Mock
    private DogService dogService;

    @InjectMocks
    private CustomerFacadeImpl customerFacade;

    private Mapper mapper;

    @Before
    public void initDozer() {
        mapper = new DozerBeanMapper();
    }

    @Test
    public void testCreate() {
        Customer customer = mock(Customer.class);
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(beanMappingService.map(customerDTO, Customer.class)).thenReturn(customer);
        customerFacade.createCustomer(customerDTO);
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
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        Customer customer = mock(Customer.class);
        when(beanMappingService.map(customerDTO, Customer.class)).thenReturn(customer);
        customerFacade.deleteCustomer(customerDTO);
        verify(customerService).deleteCustomer(customer);
    }

    @Test
    public void testGetAll() {
        customerFacade.getAllCustomers();
        verify(customerService).getAllCustomers();
    }
}
