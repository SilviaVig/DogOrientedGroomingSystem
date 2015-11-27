package org.woofenterprise.dogs.facade;

import java.util.Collection;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.woofenterprice.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.CustomerService;
import org.woofenterprise.dogs.entity.Customer;

@Service
public class CustomerFacadeImpl implements CustomerFacade {
    
    @Inject
    BeanMappingService beanMappingService;
    
    @Inject
    CustomerService customerService;
    
    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Customer c = customerService.findCustomerById(customerId);
        return beanMappingService.map(c, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        Customer c = customerService.findCustomerByEmail(email);
        return beanMappingService.map(c, CustomerDTO.class);
    }

    @Override
    public Long createCustomer(CustomerDTO customerDTO) {
        Customer c = beanMappingService.map(customerDTO, Customer.class);
        return customerService.createCustomer(c);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        Collection<Customer> customers = customerService.getAllCustomers();
        return beanMappingService.map(customers, CustomerDTO.class);
    }
    
}
