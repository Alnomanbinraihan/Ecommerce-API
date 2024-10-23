package com.example.productManagement.interfaces;

import com.example.productManagement.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductOperations {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Long id, Product product);

    String deleteProduct(Long id);

    String updateStockQuantity(Long id, Integer stockQuantity);

    Page<Product> getPaginatedAndSortedProducts(int page, int size, boolean isAsc);
}
