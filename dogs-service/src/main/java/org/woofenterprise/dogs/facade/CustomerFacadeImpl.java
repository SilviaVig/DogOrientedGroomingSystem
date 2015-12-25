package org.woofenterprise.dogs.facade;

import java.util.Collection;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woofenterprise.dogs.dto.CustomerAuthenticationDTO;
import org.woofenterprise.dogs.dto.CustomerCreateDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.service.BeanMappingService;
import org.woofenterprise.dogs.service.CustomerService;
import org.woofenterprise.dogs.entity.Customer;

@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade {
    
    @Inject
    BeanMappingService beanMappingService;
    
    @Inject
    CustomerService customerService;
    
    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        Customer c = customerService.findCustomerById(customerId);
        return (c == null) ? null : beanMappingService.map(c, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        Customer c = customerService.findCustomerByEmail(email);
        return (c == null) ? null : beanMappingService.map(c, CustomerDTO.class);
    }

    @Override
    public Long createCustomer(CustomerCreateDTO customerCreateDTO) {
        Customer c = beanMappingService.map(customerCreateDTO, Customer.class);
        customerService.createCustomer(c);
        return c.getId();
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        Customer c = beanMappingService.map(customerDTO, Customer.class);
        customerService.deleteCustomer(c);
    }

    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        Collection<Customer> customers = customerService.getAllCustomers();
        return beanMappingService.map(customers, CustomerDTO.class);
    }

    @Override
    public CustomerDTO changeEmail(Long customerId, String newEmail) {
        Customer customer = customerService.changeEmail(customerId, newEmail);
        return beanMappingService.map(customer, CustomerDTO.class);
    }

    @Override
    public void update(CustomerDTO customer) {
        Customer c = beanMappingService.map(customer, Customer.class);
        customerService.update(c);
    }

    @Override
    public boolean authenticate(CustomerAuthenticationDTO customerAuthenticationDTO) {
        return customerService.authenticate(customerService.findCustomerByEmail(
                customerAuthenticationDTO.getEmail()), customerAuthenticationDTO.getPassword());
    }

    @Override
    public boolean isAdmin(CustomerDTO customer) {
        Customer c = beanMappingService.map(customer, Customer.class);
        return customerService.isAdmin(c);
    }

}
