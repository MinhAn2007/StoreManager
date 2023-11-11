package vn.edu.iuh.fit.wwwlab02.backend.dto;

import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;

public class ProductInfoDTO {

   private Long productId;
   private String name ;
   private String description;
   private String unit ;
   private String manufacturer;
   private String path;

   private double price;

    public ProductInfoDTO(Long productId, String name, String description, String unit, String manufacturer, String path, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturer = manufacturer;
        this.path = path;
        this.price = price;
    }

    public ProductInfoDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
