package vn.edu.iuh.fit.wwwlab02.backend.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.dto.OrderDto;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Order;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;
import vn.edu.iuh.fit.wwwlab02.backend.services.CustomerService;
import vn.edu.iuh.fit.wwwlab02.backend.services.EmployeeService;
import vn.edu.iuh.fit.wwwlab02.backend.services.OrderService;
import vn.edu.iuh.fit.wwwlab02.backend.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Path("/orders")
public class OrderResource {
    private final OrderService orderService = new OrderService();
    private final CustomerService customerService = new CustomerService();
    private final EmployeeService employeeService = new EmployeeService();
    private final ProductService productService = new ProductService();

    public OrderResource() {
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Order> list = orderService.getAllOrders();
        return Response.ok(list).build();
    }

    @GET
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID() {
        if (orderService.getInfoOrder().isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(orderService.getInfoOrder()).build();
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderDto(@PathParam("id") long id) {
        if (orderService.findOrder(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(orderService.findOrder(id).get()).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(List<OrderDto> orderDtos) {
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderDto orderDto : orderDtos) {
            Order order = new Order();
            OrderDetail orderDetail = new OrderDetail();
            order.setOrderDate(orderDto.getOrderDate());
            order.setCustomer(customerService.findCus(orderDto.getCusId()).orElse(null));
            order.setEmployee(employeeService.findEmployee(orderDto.getEnoId()).orElse(null));
            orderDetail.setNote(orderDto.getNote());
            orderDetail.setPrice(orderDto.getPrice());
            orderDetail.setQuantity(orderDto.getQuantity());
            orderDetail.setProduct(productService.findProduct(orderDto.getProductId()).orElse(null));
            orderDetail.setOrder(order);
            orders.add(order);
            orderDetails.add(orderDetail);
            orderService.insertOrder(order ,orderDetail);

        }

        return Response.ok(orders).build();
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