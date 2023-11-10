package vn.edu.iuh.fit.wwwlab02.backend.dto;

public class ProductInfoDTO {
    private String name;
    private Double price;
    private String description;
    private String manufacturer;
    private String imagePath;

    // Các phương thức getter và setter cho các trường dữ liệu


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ProductInfoDTO(String name, Double price, String description, String manufacturer, String imagePath) {
        this.manufacturer = manufacturer;

        this.description = description;

        this.price = price;
        this.imagePath = imagePath;
        this.name = name;

    }

    public ProductInfoDTO() {
    }
}
