package vn.edu.iuh.fit.wwwlab02.backend.services;

import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductImage;
import vn.edu.iuh.fit.wwwlab02.backend.repositories.ProductImageRepository;

import java.util.List;
import java.util.Optional;

public class ProductImageService {
    private final ProductImageRepository repo=new ProductImageRepository();

    public ProductImageService() {

    }

    public boolean insertProductImage(ProductImage productImage) {
        return repo.insertProductImage(productImage);
    }

    public boolean updateProductImage(ProductImage productImage) {
        return repo.updateProductImage(productImage);
    }

    public Optional<ProductImage> findProductImage(long id) {
        return repo.findProductImage(id);
    }

    public boolean deleteProductImage(long id) {
        return repo.deleteProductImage(id);
    }

    public List<ProductImage> getAllProductImages() {
        return repo.getAllProductImages();
    }

}
