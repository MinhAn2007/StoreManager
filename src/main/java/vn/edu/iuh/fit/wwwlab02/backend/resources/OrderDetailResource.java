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
    @Path("/{order_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("order_id") long orderID) {
        List<OrderDetail> orderDetails = service.findOrderDetail(orderID);
        if (orderDetails.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(orderDetails).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(OrderDetail orderDetail){
        service.insertOrderDetail(orderDetail);
        return Response.ok(orderDetail).build();
    }



}