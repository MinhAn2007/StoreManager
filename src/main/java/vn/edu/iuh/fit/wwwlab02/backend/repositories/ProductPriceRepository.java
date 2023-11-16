package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Customer;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProductPriceRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductPriceRepository() {
        em = Persistence.createEntityManagerFactory("Lab_02").createEntityManager();
        trans=em.getTransaction();
    }

    public void insertProductPrice(ProductPrice productPrice){
        try {
            trans.begin();
            em.persist(productPrice);
            trans.commit();
        }catch (Exception e){
            logger.info(e.getMessage());
            trans.rollback();
        }
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

    public List<ProductPrice> findByIdNotDate(Long id){
        return em.createQuery("SELECT c FROM ProductPrice c WHERE c.product.product_id = :id ORDER BY c.product.product_id ASC", ProductPrice.class)
                .setParameter("id", id)
                .getResultList();    }
    public List<ProductPrice> getAllProductPrice() {
        try {
            trans.begin();
            List<ProductPrice> list = em.createQuery("SELECT c from ProductPrice c order by c.product.product_id ASC", ProductPrice.class).getResultList();
            trans.commit();
            return list;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
        }
        return null;
    }
    public Double findLatestProductPrice(long productId) {
        String jpql = "SELECT pp.price " +
                "FROM ProductPrice pp " +
                "WHERE pp.product.product_id = :productId " +
                "ORDER BY pp.price_date_time DESC";

        TypedQuery<Double> query = em.createQuery(jpql, Double.class);
        query.setParameter("productId", productId);
        query.setMaxResults(1);  // Giới hạn kết quả chỉ trả về một giá

        Double latestPrice = null;

        try {
            latestPrice = query.getSingleResult();
        } catch (NoResultException e) {
            // Xử lý khi không tìm thấy kết quả nếu cần
        }

        return latestPrice;
    }
}
