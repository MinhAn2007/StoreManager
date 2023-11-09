package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;

import java.util.List;


public class CustomerRepository {
        private EntityManager em;
        private EntityTransaction trans;

        private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        public CustomerRepository() {
            em = Persistence
                    .createEntityManagerFactory("Lab_02")
                    .createEntityManager();
            trans = em.getTransaction();
        }

        public void insertCust(Customer customer) {
            try {
                trans.begin();
                em.persist(customer);
                trans.commit();
            } catch (Exception ex) {
                trans.rollback();
                logger.error(ex.getMessage());
            }
        }

        public List<Customer> getAllCust() {
            return em.createQuery("select c from Customer c", Customer.class).getResultList();
        }
    }

