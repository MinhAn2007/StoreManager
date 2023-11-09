package vn.edu.iuh.fit.wwwlab02.backend.resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Employee;
import vn.edu.iuh.fit.wwwlab02.backend.services.EmployeeService;

import java.util.List;

@Path("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService = new EmployeeService();

    public EmployeeResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Employee employee) {
        employeeService.insertEmployee(employee);
        return Response.ok(employee).build();
    }

    //    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(@PathParam("id") long id, Customer customer) {
//        Customer cus = customerService.finCustomer(id).get();
//        if (cus == null)
//            return Response.status(Response.Status.NOT_FOUND).build();
//        boolean update = customerService.updateCustomer(customer);
//        if (!update)
//            return Response.status(Response.Status.NOT_FOUND).build();
//        return Response.ok(customer).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findByID(@PathParam("id") long id) {
//        Customer customer = customerService.finCustomer(id).get();
//        if (customer == null)
//            return Response.status(Response.Status.NOT_FOUND).build();
//        return Response.ok(customer).build();
//    }
//
    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Employee> list = employeeService.getAllEmployee();
        return Response.ok(list).build();
    }
//
//    @DELETE
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response delete(@PathParam("id") long id) {
//        boolean delete = customerService.deleteCustomer(id);
//        if (!delete)
//            return Response.status(Response.Status.NOT_FOUND).build();
//        return Response.ok(id).build();
//    }
}