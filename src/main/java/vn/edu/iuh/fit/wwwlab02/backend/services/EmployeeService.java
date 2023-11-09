package vn.edu.iuh.fit.wwwlab02.backend.services;

import vn.edu.iuh.fit.wwwlab02.backend.entities.Employee;
import vn.edu.iuh.fit.wwwlab02.backend.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository repo=new EmployeeRepository();

    public EmployeeService() {

    }

    public boolean insertEmployee(Employee employee) {return repo.insertEmployee(employee);}
    public boolean updateEmployee(Employee employee) {return repo.updateEmployee(employee);}
    public Optional<Employee> findEmployee(long id) {return repo.findEmployee(id);}
    public boolean deleteEmployee(long id) {return repo.deleteEmployee(id);}
    public List<Employee> getAllEmployee(){return repo.getAllEmployee();}
    public List<Employee> getActiveEmployee(){return repo.getActiveEmployee();}
}
