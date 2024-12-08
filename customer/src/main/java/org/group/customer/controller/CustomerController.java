package org.group.customer.controller;


import org.group.customer.entity.Customer;
import org.group.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        Customer savedCustomer = service.registerCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String passwordHash) {
        Customer customer = service.login(email, passwordHash);
        if (customer != null) {
            return ResponseEntity.ok("Login Successful!");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = service.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
