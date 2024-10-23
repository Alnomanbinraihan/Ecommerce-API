package com.example.productManagement.service;
import com.example.productManagement.entity.Category;
import com.example.productManagement.interfaces.CategoryOperations;
import com.example.productManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryOperations {

    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Long id, String name) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        category.setCategoryName(name);
        return categoryRepository.save(category);
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category has been deleted.";
    }

    public Page<Category> getPaginatedAndSortedCategory(int page, int size, boolean isAsc) {

        Pageable pageable;
        if(isAsc) {
            pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryRepository.findAll(pageable);
    }
}
