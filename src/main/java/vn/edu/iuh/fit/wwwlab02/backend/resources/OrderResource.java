package vn.edu.iuh.fit.wwwlab02.backend.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.dto.OrderDto;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Order;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;
import vn.edu.iuh.fit.wwwlab02.backend.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/orders")
public class OrderResource {
    private final OrderService orderService = new OrderService();
    private final CustomerService customerService = new CustomerService();
    private final EmployeeService employeeService = new EmployeeService();
    private final ProductService productService = new ProductService();

    private final OrderDetailService orderDetailService = new OrderDetailService();

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
            Order existingOrder = orderService.findOrderByCustomerAndEmployee(orderDto.getCusId(), orderDto.getEnoId());

            if (existingOrder == null) {
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
                orderService.insertOrder(order, orderDetail);
            } else {
                Optional<OrderDetail> orderDetailOptional = orderDetailService.findOrderDetailByOrderAndProduct(existingOrder, productService.findProduct(orderDto.getProductId()).orElse(null));

                if (orderDetailOptional.isPresent()) {
                    OrderDetail orderDetail = orderDetailOptional.get();
                } else {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setNote(orderDto.getNote());
                    orderDetail.setPrice(orderDto.getPrice());
                    orderDetail.setQuantity(orderDto.getQuantity());
                    orderDetail.setProduct(productService.findProduct(orderDto.getProductId()).orElse(null));
                    orderDetail.setOrder(existingOrder);
                    orderDetails.add(orderDetail);
                    orderDetailService.insertOrderDetail(orderDetail);
                }
            }
        }

        return Response.ok(orders).build();
    }


    @GET
    @Path("/statisticsByDay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatisticsByDay() {
        try {
            List<Object[]> result = orderService.getOrderStatisticsByDay();
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving order statistics by day: " + e.getMessage())
                    .build();
        }
    }
    @GET
    @Path("/statisticsByEmployeeAndDateRange")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatisticsByEmployeeAndDateRange(
            @QueryParam("employeeId") Long employeeId,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {
        try {
            if (employeeId == null || startDate == null || endDate == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Employee ID, start date, and end date are required.")
                        .build();
            }

            LocalDate startLocalDate = LocalDate.parse(startDate);
            LocalDate endLocalDate = LocalDate.parse(endDate);

            List<Object[]> result = orderService.getOrderStatisticsByEmployeeAndDateRange(employeeId, startLocalDate, endLocalDate);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving order statistics by employee and date range: " + e.getMessage())
                    .build();
        }
    }
    @GET
    @Path("/statisticsByDateRange")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatisticsByDateRange(
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate) {
        try {
            LocalDate startLocalDate = LocalDate.parse(startDate);
            LocalDate endLocalDate = LocalDate.parse(endDate);

            List<Object[]> result = orderService.getOrderStatisticsByDateRange(startLocalDate, endLocalDate);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving order statistics by date range: " + e.getMessage())
                    .build();
        }
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