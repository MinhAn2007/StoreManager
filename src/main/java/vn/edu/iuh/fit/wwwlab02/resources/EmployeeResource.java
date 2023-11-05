package vn.edu.iuh.fit.wwwlab02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.entities.Employee;
import vn.edu.iuh.fit.wwwlab02.services.EmployeeService;

import java.util.List;

@Path("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService = new EmployeeService();

    public EmployeeResource() {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Employee> list = employeeService.getAllEmployee();
        return Response.ok(list).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActive() {
        List<Employee> list = employeeService.getActiveEmployee();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id) {
        if(employeeService.findEmployee(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(employeeService.findEmployee(id).get()).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Employee employee){
        employeeService.insertEmployee(employee);
        return Response.ok(employee).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id,Employee employee){
        if(employeeService.findEmployee(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update= employeeService.updateEmployee(employee);
        if(!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(employee).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){
        boolean delete=employeeService.deleteEmployee(id);
        if(!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}