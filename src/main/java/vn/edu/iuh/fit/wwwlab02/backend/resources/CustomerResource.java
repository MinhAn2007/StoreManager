package vn.edu.iuh.fit.wwwlab02.backend.resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.backend.services.CustomerService;

import java.util.List;
import java.util.Optional;

@Path("/customers")
public class CustomerResource {
    private final CustomerService customerService = new CustomerService();

    public CustomerResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Customer customer) {
        customerService.insertCust(customer);
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id,Customer customer) {
        Optional<Customer> optionalCustomer = customerService.findCus(id);
        if (optionalCustomer.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean update = customerService.updateCus(customer);
        if (!update) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id) {
        Optional<Customer> optionalCustomer = customerService.findCus(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return Response.ok(customer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Customer> list = customerService.getAll();
        return Response.ok(list).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        boolean delete = customerService.deleteCus(id);
        if (!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}