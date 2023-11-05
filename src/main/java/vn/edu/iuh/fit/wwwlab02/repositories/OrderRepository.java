package vn.edu.iuh.fit.wwwlab02.repositories;

import jakarta.persistence.*;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.entities.Order;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class OrderRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().getName());

    public OrderRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertOrder(Order orders) {
        try {
            trans.begin();
            em.persist(orders);
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
}
