package vn.edu.iuh.fit.wwwlab02.repositories;

import jakarta.persistence.*;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.entities.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProductPriceRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private Logger logger= (Logger) LoggerFactory.getLogger(this.getClass().getName());

    public ProductPriceRepository() {
        em= Persistence.createEntityManagerFactory("default").createEntityManager();
        trans=em.getTransaction();
    }

    public boolean insertProductPrice(ProductPrice productPrice){
        try {
            trans.commit();
            em.persist(productPrice);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }
    public boolean updateProductPrice(ProductPrice productPrice){
        try {
            trans.commit();
            em.merge(productPrice);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public Optional<ProductPrice> findProductPrice(long id, LocalDateTime dateTime) {
        TypedQuery<ProductPrice> query = em.createQuery("select o from ProductPrice o where o.product.product_id=:id and o.price_date_time=:date", ProductPrice.class);
        query.setParameter("id", id);
        query.setParameter("date", dateTime);
        ProductPrice productPrice = query.getSingleResult();
        return productPrice == null ? Optional.empty() : Optional.of(productPrice);
    }

    public boolean deleteProductPrice(long id,LocalDateTime dateTime) {
        Optional<ProductPrice> op = findProductPrice(id, dateTime);
        ProductPrice productPrice = op.isPresent() ? op.get() : null;
        if (productPrice == null) return false;
        try {
            trans.begin();
            em.remove(productPrice);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public List<ProductPrice> getAllProductPrice() {
        try {
            trans.begin();
            List<ProductPrice> list = em.createNativeQuery("SELECT * from product_price ", ProductPrice.class).getResultList();
            trans.commit();
            return list;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }
}
