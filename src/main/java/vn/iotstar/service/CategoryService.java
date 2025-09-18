package vn.iotstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    // Basic CRUD operations
    public List<Category> findAll() {
        return categoryRepository.findAllOrderByCreatedAtDesc();
    }
    
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
    
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }
    
    public Optional<Category> findByCateid(Integer cateid) {
        return categoryRepository.findByCateid(cateid);
    }
    
    
    // Save operations
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    
    // Delete operations
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
    
    public void deleteByCateid(Integer cateid) {
        categoryRepository.deleteByCateid(cateid);
    }
    
    // Search operations with pagination
    public Page<Category> searchByName(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            return categoryRepository.findAll(pageable);
        }
        return categoryRepository.findByCatenameContainingIgnoreCase(name.trim(), pageable);
    }
    
    
    public List<Category> findByName(String name) {
        return categoryRepository.findByCatenameContaining(name);
    }
    

    // Validation operations
    public boolean existsById(Integer id) {
        return categoryRepository.existsById(id);
    }
    
    public boolean existsByName(String name) {
        return categoryRepository.existsByCatenameIgnoreCase(name);
    }
    
    public boolean existsByNameAndNotId(String name, Integer id) {
        return categoryRepository.existsByCatenameIgnoreCaseAndCateidNot(name, id);
    }
    
    // Count operations
    public long count() {
        return categoryRepository.count();
    }
    
    public long countAllCategories() {
        return categoryRepository.count();
    }
    
    
    // Create and Update operations for AdminController
    public Category createCategory(Category category) {
        // Validate category name doesn't exist
        if (existsByName(category.getCatename())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        return categoryRepository.save(category);
    }
    
    public Category updateCategory(Category category) {
        // Validate category exists
        if (!existsById(category.getCateid())) {
            throw new IllegalArgumentException("Category not found");
        }
        // Validate category name doesn't exist for other categories
        if (existsByNameAndNotId(category.getCatename(), category.getCateid())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        return categoryRepository.save(category);
    }
}