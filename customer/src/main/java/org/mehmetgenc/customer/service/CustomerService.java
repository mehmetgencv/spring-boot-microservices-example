package org.mehmetgenc.customer.service;

import org.mehmetgenc.customer.dto.CustomerRegistrationRequest;
import org.mehmetgenc.customer.model.Customer;
import org.mehmetgenc.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.save(customer);
    }

}
