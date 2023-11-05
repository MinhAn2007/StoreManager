package vn.edu.iuh.fit.wwwlab02.services;

import vn.edu.iuh.fit.wwwlab02.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository repository = new CustomerRepository();

    public CustomerService() {
//        repository = new CustomerRepository();
    }

    public void insertCust(Customer customer) {
        repository.insertCust(customer);
    }

    public List<Customer> getAll() {
        return repository.getAllCust();
    }}