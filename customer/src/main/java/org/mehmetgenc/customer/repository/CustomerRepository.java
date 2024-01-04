package org.mehmetgenc.customer.repository;

import org.mehmetgenc.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
