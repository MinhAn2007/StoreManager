package vn.edu.iuh.fit.wwwlab02.backend.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;
import vn.edu.iuh.fit.wwwlab02.backend.services.OrderDetailService;

import java.util.List;

@Path("/orderDetails")
public class OrderDetailResource {
    private final OrderDetailService service= new OrderDetailService();

    public OrderDetailResource() {
    }
    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<OrderDetail> list= service.getAllOrderDetails();
        return Response.ok(list).build();
    }
    @GET
    @Path("/{order_id}/{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("order_id") long orderID, @PathParam("product_id") long productID){
        if(service.findOrderDetail(orderID, productID).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(service.findOrderDetail(orderID, productID).get()).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(OrderDetail orderDetail){
        service.insertOrderDetail(orderDetail);
        return Response.ok(orderDetail).build();
    }
    @PUT
    @Path("/{order_id}/{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("order_id") long orderID,@PathParam("product_id") long productID,OrderDetail orderDetail){
        if(service.findOrderDetail(orderID, productID).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update=service.updateOrderDetail(orderDetail);
        if(!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(orderDetail).build();
    }
    @DELETE
    @Path("/{order_id}/{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("order_id") long orderID,@PathParam("product_id") long productID){
        boolean update=service.deleteOrderDetail(orderID, productID);
        if(!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(true).build();
    }

}