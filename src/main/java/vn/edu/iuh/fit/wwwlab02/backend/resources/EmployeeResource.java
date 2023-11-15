package vn.edu.iuh.fit.wwwlab02.backend.resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.converts.EmployeeStatusConvert;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Employee;
import vn.edu.iuh.fit.wwwlab02.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.wwwlab02.backend.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@Path("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService = new EmployeeService();

    public EmployeeResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Employee employee) {
        EmployeeStatusConvert employeeStatusConvert = new EmployeeStatusConvert();
        Integer statusInDatabase = employeeStatusConvert.convertToDatabaseColumn(employee.getStatus());
        System.out.println(statusInDatabase);
        employee.setStatus(EmployeeStatus.fromcode(statusInDatabase));
        employeeService.insertEmployee(employee);
        return Response.ok(employee).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Employee employee) {
        Optional<Employee> emp = employeeService.findEmployee(id);
        if (emp.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean update = employeeService.updateEmployee(employee);
        if (!update) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(employee).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id) {
        Optional<Employee> optionalEmployee = employeeService.findEmployee(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return Response.ok(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Employee> list = employeeService.getAllEmployee();
        return Response.ok(list).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        boolean delete = employeeService.deleteEmployee(id);
        if (!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}