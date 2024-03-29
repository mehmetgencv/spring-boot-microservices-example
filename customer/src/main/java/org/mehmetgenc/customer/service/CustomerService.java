package org.mehmetgenc.customer.service;

import org.mehmetgenc.customer.dto.CustomerRegistrationRequest;
import org.mehmetgenc.customer.dto.FraudCheckResponse;
import org.mehmetgenc.customer.model.Customer;
import org.mehmetgenc.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }


    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse =  restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudester");
        }
    }

}
