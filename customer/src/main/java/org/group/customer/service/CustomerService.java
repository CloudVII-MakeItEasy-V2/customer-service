package org.group.customer.service;


import org.group.customer.entity.Customer;
import org.group.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer registerCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer login(String email, String passwordHash) {
        Customer customer = repository.findByEmail(email);
        if (customer != null && customer.getPasswordHash().equals(passwordHash)) {
            return customer;
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
