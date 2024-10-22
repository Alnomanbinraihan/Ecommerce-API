package com.example.productManagement.controller;
import com.example.productManagement.entity.Product;
import com.example.productManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.isPresent() ? ResponseEntity.ok(product.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getPaginatedAndSortedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean isAsc) {

        return ResponseEntity.ok(productService.getPaginatedAndSortedProducts(page, size, isAsc));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PatchMapping("/{id}/update-stock")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestParam Integer stockQuantity) {        ;
        return ResponseEntity.ok(productService.updateStockQuantity(id, stockQuantity));
    }
}
