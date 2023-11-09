package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;

import java.util.List;
import java.util.Optional;

public class OrderDetailsRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public OrderDetailsRepository() {
        em = Persistence.createEntityManagerFactory("Lab_02").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        try {
            trans.begin();
            em.persist(orderDetail);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        try {
            trans.begin();
            em.merge(orderDetail);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public Optional<OrderDetail> findOrderDetail(long OrderID, long ProductID) {
        OrderDetail orderDetail = (OrderDetail) em.createNativeQuery("Select od from order_detail od where od.order_id = " + OrderID + " and od.product_id = " + ProductID + " ", OrderDetail.class).getSingleResult();
        return orderDetail == null ? Optional.empty() : Optional.of(orderDetail);

    }

    public boolean deleteOrderDetail(long OrderID, long ProductID) {
        Optional<OrderDetail> op = findOrderDetail(OrderID, ProductID);
        OrderDetail orderDetail = op.isPresent() ? op.get() : null;
        if (orderDetail == null) return false;
        try {
            trans.begin();
            em.remove(orderDetail);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }
    public List<OrderDetail> getAllOrderDetails() {
        try {
            trans.begin();
            String query = "SELECT od.*, pp.price AS latest_price, od.product_id, od.order_id " +
                    "FROM order_detail od " +
                    "INNER JOIN (SELECT product_id, MAX(price_date_time) AS latest_date " +
                    "            FROM product_price " +
                    "            GROUP BY product_id) latest_prices " +
                    "ON od.product_id = latest_prices.product_id " +
                    "INNER JOIN product_price pp " +
                    "ON od.product_id = pp.product_id AND latest_prices.latest_date = pp.price_date_time";

            List<OrderDetail> list = em.createNativeQuery(query, OrderDetail.class).getResultList();
            trans.commit();
            return list;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }


}
