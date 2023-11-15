package vn.edu.iuh.fit.wwwlab02.backend.dto;


import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductImage;
import vn.edu.iuh.fit.wwwlab02.backend.enums.ProductStatus;

public class ProductAndImageDTO {
    private String productName;
    private String description;
    private String unit;
    private String manufacturer;

    private ProductStatus status;

    private Long id;
    private String path;
    private String alternative;

    private double price;

    public ProductAndImageDTO(String productName, String description, String unit, String manufacturer, ProductStatus status, Long id, String path, String alternative, double price) {
        this.productName = productName;
        this.description = description;
        this.unit = unit;
        this.manufacturer = manufacturer;
        this.status = status;
        this.id = id;
        this.path = path;
        this.alternative = alternative;
        this.price = price;
    }

    public ProductAndImageDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
