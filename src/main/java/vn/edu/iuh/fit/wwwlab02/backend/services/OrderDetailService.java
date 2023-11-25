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

    public List<OrderDetail> findOrderDetail(long OrderID) {
        return repo.findOrderDetail(OrderID);
    }



    public List<OrderDetail> getAllOrderDetails() {
        return repo.getAllOrderDetails();
    }
}
