package vn.edu.iuh.fit.wwwlab02.backend.services;

import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.backend.repositories.CustomerRepository;

import java.util.List;

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