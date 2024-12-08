package org.group.customer.controller;


import jakarta.websocket.server.PathParam;
import org.group.customer.entity.Customer;
import org.group.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        Customer savedCustomer = service.registerCustomer(customer);
        if (savedCustomer == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Customer registration failed due to invalid input or business logic.");
        }
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

    @GetMapping("/getInformationById/{id}")
    public ResponseEntity<Customer> getInformationById(@PathVariable("id") int id) {
        if (!service.existId(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Customer());
        }
        Customer customer = service.findById(id).get();
        return ResponseEntity.ok(customer);
    }

}
