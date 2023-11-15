package vn.edu.iuh.fit.wwwlab02.backend.services;

import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.backend.repositories.CustomerRepository;

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
    }

    public Optional<Customer> findCus(long id) {
        return repository.findCus(id);
    }
    public boolean updateCus(Customer customer) {return repository.updateCus(customer);}
    public boolean deleteCus(long id) {return repository.deleteCus(id);}

}