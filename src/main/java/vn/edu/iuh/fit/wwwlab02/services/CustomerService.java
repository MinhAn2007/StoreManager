package vn.edu.iuh.fit.wwwlab02.services;

import vn.edu.iuh.fit.wwwlab02.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository repo=new CustomerRepository();

    public CustomerService() {

    }

    public boolean insertCustomer(Customer customer) {
        return repo.insertCustomer(customer);
    }

    public boolean updateCustomer(Customer customer) {
        return repo.updateCustomer(customer);
    }

    public Optional<Customer> finCustomer(long id) {
        return repo.finCustomer(id);
    }

    public boolean deleteCustomer(long id) {
        return repo.deleteCustomer(id);
    }

    public List<Customer> getAllCusTomer() {
        return repo.getAllCusTomer();
    }
}
