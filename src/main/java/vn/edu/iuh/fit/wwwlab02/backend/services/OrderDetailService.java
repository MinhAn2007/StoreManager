package vn.edu.iuh.fit.wwwlab02.backend.services;

import vn.edu.iuh.fit.wwwlab02.backend.repositories.OrderDetailsRepository;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;

import java.util.List;
import java.util.Optional;

public class OrderDetailService {
    private final OrderDetailsRepository repo=new OrderDetailsRepository();

    public OrderDetailService() {

    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        return repo.insertOrderDetail(orderDetail);
    }

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        return repo.updateOrderDetail(orderDetail);
    }

    public Optional<OrderDetail> findOrderDetail(long OrderID, long ProductID) {
        return repo.findOrderDetail(OrderID, ProductID);
    }

    public boolean deleteOrderDetail(long OrderID, long ProductID) {
        return repo.deleteOrderDetail(OrderID, ProductID);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return repo.getAllOrderDetails();
    }
}
