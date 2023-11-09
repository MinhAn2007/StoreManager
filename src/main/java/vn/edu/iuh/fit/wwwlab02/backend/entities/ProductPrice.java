package vn.edu.iuh.fit.wwwlab02.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import vn.edu.iuh.fit.wwwlab02.backend.pks.ProductPricePK;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_price")
@IdClass(ProductPricePK.class)
public class ProductPrice {
    @Id
    @JoinColumn(name = "product_id")
    @JsonIgnore
    @ManyToOne
    private Product product;
    @Id
    @Column(name = "price_date_time")
    private LocalDateTime price_date_time;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "note")
    private String note;
    @Basic
    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId; // Thuộc tính productId
    public ProductPrice(Long productId) {
        this.productId = productId;
    }

    public ProductPrice() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductPrice(Product product, LocalDateTime price_date_time, double price, String note, Long productId) {
        this.product = product;
        this.price_date_time = price_date_time;
        this.price = price;
        this.note = note;
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getPrice_date_time() {
        return price_date_time;
    }

    public void setPrice_date_time(LocalDateTime price_date_time) {
        this.price_date_time = price_date_time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product=" + product.getProduct_id() + product.getName()+
                ", price_date_time=" + price_date_time +
                ", price=" + price +
                ", note='" + note + '\'' +
                '}';
    }
}