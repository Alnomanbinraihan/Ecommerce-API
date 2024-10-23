package com.example.productManagement.interfaces;

import com.example.productManagement.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryOperations {

    Category createCategory(String name);
    List<Category> getAllCategory();
    Optional<Category> getCategoryById(Long id);
    Category updateCategory(Long id, String name);
    String deleteCategory(Long id);
    Page<Category> getPaginatedAndSortedCategory(int page, int size, boolean isAsc);
}
