package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Employee;

import java.util.List;
import java.util.Optional;


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


    public List<Customer> getAllCust(){
        try {
            trans.begin();
            List<Customer> list= em.createNativeQuery("Select * from customer order by cust_name ", Customer.class).getResultList();
            trans.commit();
            return list;
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }
    public Optional<Customer> findCus(long id) {
        Customer customer = em.find(Customer.class, id);
        return Optional.ofNullable(customer);
    }

    public boolean updateCus(Customer customer) {
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

    public boolean deleteCus(long id) {
        Optional<Customer> optionalCustomer = findCus(id);

        try {
            if (optionalCustomer.isPresent()) {
                trans.begin();
                em.remove(optionalCustomer.get());
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
}

