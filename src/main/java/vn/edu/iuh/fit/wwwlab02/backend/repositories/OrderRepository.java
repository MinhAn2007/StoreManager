package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.dto.OrderDto;
import vn.edu.iuh.fit.wwwlab02.backend.dto.ProductInfoDTO;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Order;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;

import java.time.LocalDate;
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

    public boolean insertOrder(Order orders, OrderDetail orderDetail) {
        try {
            trans.begin();
            em.persist(orders);
            em.persist(orderDetail);
            em.flush();
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
        try {
            trans.begin();
            Optional<Order> op = findOrder(id);
            if (op.isPresent()) {
                Order order = op.get();
                order.getOrderDetails().get(0).setNote("DELETED");
                em.merge(order);
                trans.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
            return false;
        }
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

    public Order findOrderByCustomerAndEmployee(Long customerId, Long employeeId) {
        try {
            String queryString = "SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.employee.id = :employeeId";
            TypedQuery<Order> query = em.createQuery(queryString, Order.class);
            query.setParameter("customerId", customerId);
            query.setParameter("employeeId", employeeId);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }


    public List<OrderDto> getInfoOrder() {
        try {
            trans.begin();

            List<Order> list = em.createQuery("select distinct o from Order o join o.orderDetails od order by o.order_id asc ", Order.class)
                    .getResultList();

            List<OrderDto> orderDtoList = new ArrayList<>();

            for (Order order : list) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderId(order.getOrder_id());
                orderDto.setOrderDate(order.getOrderDate());
                orderDto.setNote(order.getOrderDetails().get(0).getNote());
                orderDto.setCusId(order.getCustomer().getId());
                orderDto.setEnoId(order.getEmployee().getId());
                orderDto.setEmployeeName(order.getEmployee().getFullname());
                orderDto.setCustomerName(order.getCustomer().getName());

                OrderDetail orderDetail = order.getOrderDetails().get(0);
                orderDto.setProductId(orderDetail.getProduct().getProduct_id());
                orderDto.setProductName(orderDetail.getProduct().getName());
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


    public List<Object[]> getOrderStatisticsByDay() {
        try {
            trans.begin();

            String queryString = "SELECT FUNCTION('DATE', o.orderDate) AS orderDay, COUNT(o) AS orderCount, SUM(od.price * od.quantity) AS totalAmount " + "FROM Order o JOIN o.orderDetails od " + "GROUP BY orderDay " + "ORDER BY FUNCTION('DATE', o.orderDate)";

            List<Object[]> result = em.createQuery(queryString).getResultList();

            trans.commit();
            return result;
        } catch (Exception e) {
            logger.error("Error retrieving order statistics by day: " + e.getMessage());
            trans.rollback();
        }
        return null;
    }

    public List<Object[]> getOrderStatisticsByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            trans.begin();

            String queryString = "SELECT FUNCTION('DATE', o.orderDate) AS orderDay, COUNT(o) AS orderCount, SUM(od.price * od.quantity) AS totalAmount " + "FROM Order o JOIN o.orderDetails od " + "WHERE FUNCTION('DATE', o.orderDate) BETWEEN :startDate AND :endDate " + "GROUP BY orderDay " + "ORDER BY FUNCTION('DATE', o.orderDate)";

            List<Object[]> result = em.createQuery(queryString).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();

            trans.commit();
            return result;
        } catch (Exception e) {
            logger.error("Error retrieving order statistics by date range: " + e.getMessage());
            trans.rollback();
        }
        return null;
    }

    public List<Object[]> getOrderStatisticsByEmployeeAndDateRange(Long employeeId, LocalDate startDate, LocalDate endDate) {
        try {
            trans.begin();
            String queryString = "SELECT e.fullname AS employeeName, COUNT(o) AS orderCount, " +
                    "SUM(od.price * od.quantity) AS totalAmount " +
                    "FROM Order o " +
                    "JOIN o.employee e " +
                    "JOIN o.orderDetails od " +
                    "WHERE e.id = :employeeId " +
                    "AND FUNCTION('DATE', o.orderDate) BETWEEN :startDate AND :endDate " +
                    "GROUP BY employeeName " +
                    "ORDER BY employeeName";

            List<Object[]> result = em.createQuery(queryString)
                    .setParameter("employeeId", employeeId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();

            trans.commit();
            return result;
        } catch (Exception e) {
            logger.error("Error retrieving order statistics by employee and date range: " + e.getMessage());
            trans.rollback();
        }
        return null;
    }
}


