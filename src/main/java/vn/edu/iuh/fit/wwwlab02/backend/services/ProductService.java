package vn.edu.iuh.fit.wwwlab02.backend.services;

import vn.edu.iuh.fit.wwwlab02.backend.dto.ProductInfoDTO;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductImage;
import vn.edu.iuh.fit.wwwlab02.backend.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepository repo=new ProductRepository();

    public ProductService() {

    }

    public boolean insertProduct(Product product, ProductImage productImage) {
        return repo.insertProduct(product,productImage);
    }

    public boolean updateProduct(Product product) {
        return repo.updateProduct(product);
    }

    public Optional<Product> findProduct(long id) {
        return repo.findProduct(id);
    }

    public boolean deleteProduct(long id) {
        return repo.deleteProduct(id);
    }

    public List<ProductInfoDTO> getActiveProductInfo() {
        return repo.getActiveProductInfo();
    }
    public List<Product> getActiveProduct(){return repo.getActiveProduct();}
    public List<Product> getAllProduct(){return repo.getAllProducts();}

}
