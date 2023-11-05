package vn.edu.iuh.fit.wwwlab02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.entities.Order;
import vn.edu.iuh.fit.wwwlab02.services.OrderService;

import java.util.List;

@Path("/orders")
public class OrderResource {
    private final OrderService orderService = new OrderService();

    public OrderResource() {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Order> list = orderService.getAllOrders();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id) {
        if (orderService.findOrder(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(orderService.findOrder(id).get()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Order order) {
        orderService.insertOrder(order);
        return Response.ok(order).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Order order) {
        if (orderService.findOrder(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = orderService.updateOrder(order);
        if (!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(order).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        boolean delete = orderService.deleteOrders(id);
        if (!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}