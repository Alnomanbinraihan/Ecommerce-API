package com.example.productManagement.entity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void validateProduct(String name, String description, BigDecimal price, Integer stockQuantity, String category) {
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setCategory(category);
        this.description = description;
    }

    public void updateStock(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.stockQuantity = quantity;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = name;
    }

    private void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        this.price = price;
    }

    private void setStockQuantity(Integer stockQuantity) {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer");
        }
        this.stockQuantity = stockQuantity;
    }

    private void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category is required");
        }
        this.category = category;
    }
}
