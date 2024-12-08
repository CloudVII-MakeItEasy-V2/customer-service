package org.group.customer.service;


import org.group.customer.entity.Customer;
import org.group.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.group.customer.util.AddressValidator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    private final AddressValidator addressValidator;

    @Autowired
    public CustomerService(AddressValidator addressValidator) {
        this.addressValidator = addressValidator;
    }

    public Customer registerCustomer(Customer customer) {
        if (!addressValidator.isValidAddress(customer.getAddress())){
            return null;
        }
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

    public Boolean existId(int id){
        return repository.existsById(id);
    }

    public Optional<Customer> findById(int id){
        return repository.findById(id);
    }
}
