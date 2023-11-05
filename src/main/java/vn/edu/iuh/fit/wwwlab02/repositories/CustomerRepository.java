package vn.edu.iuh.fit.wwwlab02.repositories;

import jakarta.persistence.*;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.entities.Customer;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CustomerRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().getName());

    public CustomerRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertCustomer(Customer customer) {
        try {
            trans.begin();
            em.persist(customer);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public boolean updateCustomer(Customer customer) {
        try {
            trans.begin();
            em.merge(customer);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public Optional<Customer> finCustomer(long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where c.id=:id", Customer.class);
        query.setParameter("id", id);
        Customer customer = query.getSingleResult();
        return customer == null ? Optional.empty() : Optional.of(customer);
    }
    public boolean deleteCustomer(long id) {
        Optional<Customer> op = finCustomer(id);
        Customer customer = op.isPresent() ? op.get() : null;
        if (customer == null) return false;
        try {
            trans.begin();
            em.remove(customer);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }
    public List<Customer> getAllCusTomer(){
        try {
            trans.begin();
            List list= em.createNativeQuery("Select * from customer order by cust_name", Customer.class).getResultList();
            trans.commit();
            return list;
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }
}
