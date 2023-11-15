package vn.edu.iuh.fit.wwwlab02.backend.repositories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.wwwlab02.backend.dto.ProductInfoDTO;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductImage;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductPrice;
import vn.edu.iuh.fit.wwwlab02.backend.enums.ProductStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Persistence.createEntityManagerFactory("Lab_02").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertProduct(Product product, ProductImage productImage, ProductPrice productPrice) {
        try {
            trans.begin();

            em.persist(product);
            em.persist(productImage);
            em.persist(productPrice);
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
            List<Product> list = em.createNativeQuery("Select * from product order by product_id asc ", Product.class).getResultList();
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
            trans.begin();
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

    public List<ProductInfoDTO> getActiveProductInfo() {
        try {
            trans.begin();
            List<Product> list = em.createQuery("SELECT distinct p FROM Product p JOIN FETCH p.productImageList ig JOIN FETCH p.productPrices pp WHERE p.status = :status order by pp.price_date_time desc ", Product.class)
                    .setParameter("status", ProductStatus.ACTIVE)
                    .getResultList();

            List<ProductInfoDTO> productInfoDTOs = new ArrayList<>();

            for (Product p : list) {
                ProductInfoDTO productInfoDTO = new ProductInfoDTO();
                productInfoDTO.setName(p.getName());
                productInfoDTO.setProductId(p.getProduct_id());
                productInfoDTO.setDescription(p.getDescription());
                productInfoDTO.setUnit(p.getUnit());
                productInfoDTO.setManufacturer(p.getManufacturer());
                String imagePath = p.getProductImageList().get(0).getPath();
                double price = p.getProductPrices().get(0).getPrice();
                productInfoDTO.setPath(imagePath);
                productInfoDTO.setPrice(price);
                productInfoDTOs.add(productInfoDTO);
            }

            trans.commit();
            return productInfoDTOs;
        } catch (Exception e) {
            logger.info(e.getMessage());
            trans.rollback();
            return null;
        }
    }

}
