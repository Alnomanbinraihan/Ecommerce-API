package com.example.productManagement.service;
import com.example.productManagement.entity.Product;
import com.example.productManagement.interfaces.ProductOperations;
import com.example.productManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductOperations {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct( Product product) {

        if (productRepository.findByName(product.getName().trim()).isPresent()) {
            throw new IllegalArgumentException("Product name already exists");
        }

        product.validateProduct(product.getName(), product.getDescription(), product.getPrice(), product.getStockQuantity(), product.getCategory());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.validateProduct(
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getStockQuantity(),
                updatedProduct.getCategory()
        );
        return productRepository.save(product);
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product has been deleted.";
    }

    public String updateStockQuantity(Long id, Integer stockQuantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.updateStock(stockQuantity);
        productRepository.save(product);

        return "Stock updated successfully.";
    }

    public Page<Product> getPaginatedAndSortedProducts(int page, int size, boolean isAsc) {

        Pageable pageable;
        if(isAsc) {
            pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        }
        return productRepository.findAll(pageable);
    }
}
