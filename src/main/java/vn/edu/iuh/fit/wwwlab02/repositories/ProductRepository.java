package vn.edu.iuh.fit.wwwlab02.repositories;

import jakarta.persistence.*;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.entities.Product;
import vn.edu.iuh.fit.wwwlab02.enums.ProductStatus;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProductRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertProduct(Product product) {
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        try {
            trans.begin();
            em.merge(product);
            trans.commit();
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public Optional<Product> findProduct(long id) {
        TypedQuery<Product> query = em.createQuery("select e from Product e where e.product_id=:id", Product.class);
        query.setParameter("id", id);
        Product product = query.getSingleResult();
        return product == null ? Optional.empty() : Optional.of(product);
    }

    public boolean deleteProduct(long id) {
        try {
            trans.begin();
            Optional<Product> op = findProduct(id);
            Product product = op.isPresent() ? op.get() : null;
            if (product != null) {
                product.setStatus(ProductStatus.DELETE);
                em.merge(product);
            }
            trans.commit();
            return true;

        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return false;
    }

    public List<Product> getAllProducts() {
        try {
            trans.begin();
            List<Product> list = em.createNativeQuery("Select * from product order by name ", Product.class).getResultList();
            trans.commit();
            return list;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }
    public List<Product> getActiveProduct(){
        try{
            List<Product> list=em.createQuery("Select p from Product p where p.status=:status",Product.class)
                    .setParameter("status", ProductStatus.ACTIVE)
                    .getResultList();
            trans.commit();
            return list;
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }
}
