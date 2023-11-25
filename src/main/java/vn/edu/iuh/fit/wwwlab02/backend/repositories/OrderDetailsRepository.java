package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Order;
import vn.edu.iuh.fit.wwwlab02.backend.entities.OrderDetail;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;
import vn.edu.iuh.fit.wwwlab02.backend.enums.ProductStatus;

import java.util.Collections;
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

    public List<OrderDetail> findOrderDetail(long OrderID) {
        try{
            trans.begin();
            List<OrderDetail> list= em.createQuery("select od from OrderDetail od where od.order.order_id =:OrderID",OrderDetail.class).setParameter("OrderID",OrderID).getResultList();
            trans.commit();
            return list;
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
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
    public Optional<OrderDetail> findOrderDetailByOrderAndProduct(Order order, Product product) {
        try {
            String queryString = "SELECT od FROM OrderDetail od WHERE od.order = :order AND od.product = :product";
            TypedQuery<OrderDetail> query = em.createQuery(queryString, OrderDetail.class);
            query.setParameter("order", order);
            query.setParameter("product", product);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException | NonUniqueResultException e) {
            return Optional.empty();
        }
    }

}
