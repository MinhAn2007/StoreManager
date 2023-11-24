package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.dto.OrderDto;
import vn.edu.iuh.fit.wwwlab02.backend.dto.ProductInfoDTO;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Order;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public OrderRepository() {
        em = Persistence.createEntityManagerFactory("Lab_02").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertOrder(Order orders , OrderDetail orderDetail) {
        try {
            trans.begin();
            em.persist(orders);
            em.persist(orderDetail);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public boolean updateOrder(Order orders) {
        try {
            trans.begin();
            em.merge(orders);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public Optional<Order> findOrder(long id) {
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.order_id=:id", Order.class);
        query.setParameter("id", id);
        Order orders = query.getSingleResult();
        return orders == null ? Optional.empty() : Optional.of(orders);
    }

    public boolean deleteOrders(long id) {
        Optional<Order> op = findOrder(id);
        Order orders = op.isPresent() ? op.get() : null;
        if (orders == null) return false;
        try {
            trans.begin();
            em.remove(orders);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public List<Order> getAllOrders() {
        try {
            trans.begin();
            List<Order> list = em.createNativeQuery("SELECT * from orders ", Order.class).getResultList();
            trans.commit();
            return list;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }

    public List<OrderDto> getInfoOrder() {
        try {
            trans.begin();

            // Use proper aliasing in your query to make it more readable
            List<Order> list = em.createQuery("select o from Order o join o.orderDetails od", Order.class)
                    .getResultList();

            List<OrderDto> orderDtoList = new ArrayList<>();

            for (Order order : list) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderId(order.getOrder_id()); // Assuming you have a method getOrderId() in your Order class
                orderDto.setOrderDate(order.getOrderDate());
                orderDto.setNote(order.getOrderDetails().get(0).getNote());

                orderDto.setCusId(order.getCustomer().getId()); // Assuming you have a method getCustomerId() in your Customer class
                orderDto.setEnoId(order.getEmployee().getId()); // Assuming you have a method getEmployeeId() in your Employee class
                orderDto.setEmployeeName(order.getEmployee().getFullname());
                orderDto.setCustomerName(order.getCustomer().getName());

                OrderDetail orderDetail = order.getOrderDetails().get(0);
                orderDto.setProductId(orderDetail.getProduct().getProduct_id()); // Assuming you have a method getProductId() in your Product class
                orderDto.setQuantity(orderDetail.getQuantity());
                orderDto.setPrice(orderDetail.getPrice());

                orderDtoList.add(orderDto);
            }

            trans.commit();
            return orderDtoList;
        } catch (Exception e) {
            logger.error("Error retrieving order information: " + e.getMessage());
            trans.rollback();
        }
        return null;
    }

}
