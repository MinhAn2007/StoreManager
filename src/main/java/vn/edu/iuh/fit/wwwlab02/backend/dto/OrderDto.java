package vn.edu.iuh.fit.wwwlab02.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(exclude = "success")

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private long orderId;
    private LocalDateTime orderDate;
    private long cusId;
    private long enoId;
    private String employeeName;
    private String customerName;
    private String note;
    private long productId;
    private String productName;
    private double quantity;
    private double price;

    public OrderDto(long orderId, LocalDateTime orderDate, long cusId, long enoId, String employeeName, String customerName, String note, long productId, String productName, double quantity, double price) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusId = cusId;
        this.enoId = enoId;
        this.employeeName = employeeName;
        this.customerName = customerName;
        this.note = note;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDto() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public long getCusId() {
        return cusId;
    }

    public void setCusId(long cusId) {
        this.cusId = cusId;
    }

    public long getEnoId() {
        return enoId;
    }

    public void setEnoId(long enoId) {
        this.enoId = enoId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
