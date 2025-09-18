package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;

import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    private final String UPLOAD_DIR = "uploads/";
    
    // Redirect từ root về categories
    @GetMapping("/")
    public String redirectToCategories() {
        return "redirect:/categories";
    }
    
    @GetMapping
    public String listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "cateid") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String keyword,
            Model model) {
        
        // Tạo Pageable object
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // Tìm kiếm với phân trang
        Page<Category> categoryPage;
        if (keyword != null && !keyword.trim().isEmpty()) {
            categoryPage = categoryService.searchByName(keyword, pageable);
        } else {
            categoryPage = categoryService.findAll(pageable);
        }
        
        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("totalItems", categoryPage.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        
        return "categories/list";
    }
    
    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("isEdit", false);
        return "categories/form";
    }
    
    @GetMapping("/view/{id}")
    public String viewCategory(@PathVariable Integer id, Model model) {
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            model.addAttribute("category", category);
            return "categories/view";
        }
        return "redirect:/categories";
    }
    
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Integer id, Model model) {
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            model.addAttribute("category", category);
            model.addAttribute("isEdit", true);
            return "categories/form";
        }
        return "redirect:/categories";
    }
    
    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute Category category,
                              BindingResult result,
                              @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        
        // Kiểm tra validation errors
        if (result.hasErrors()) {
            model.addAttribute("isEdit", category.getCateid() != null);
            return "categories/form";
        }
        
        // Kiểm tra tên category đã tồn tại
        try {
            if (category.getCateid() == null) {
                // Tạo mới
                if (categoryService.existsByName(category.getCatename())) {
                    result.rejectValue("catename", "error.category", "Tên category đã tồn tại");
                    model.addAttribute("isEdit", false);
                    return "categories/form";
                }
            } else {
                // Cập nhật
                if (categoryService.existsByNameAndNotId(category.getCatename(), category.getCateid())) {
                    result.rejectValue("catename", "error.category", "Tên category đã tồn tại");
                    model.addAttribute("isEdit", true);
                    return "categories/form";
                }
            }
            
            // Handle file upload
            if (iconFile != null && !iconFile.isEmpty()) {
                try {
                    String fileName = System.currentTimeMillis() + "_" + iconFile.getOriginalFilename();
                    String uploadPath = System.getProperty("user.dir") + File.separator + UPLOAD_DIR;
                    
                    // Create upload directory if it doesn't exist
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    
                    Path filePath = Paths.get(uploadPath + fileName);
                    Files.write(filePath, iconFile.getBytes());
                    
                    category.setIconFilename(fileName);
                    category.setIconPath(UPLOAD_DIR + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi tải lên hình ảnh");
                    return "redirect:/categories";
                }
            }
            
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("successMessage", 
                category.getCateid() == null ? "Tạo category thành công!" : "Cập nhật category thành công!");
            
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/categories";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Category> categoryOpt = categoryService.findById(id);
            if (categoryOpt.isPresent()) {
                categoryService.deleteById(id);
                redirectAttributes.addFlashAttribute("successMessage", "Xóa category thành công!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Category không tồn tại!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa category: " + e.getMessage());
        }
        
        return "redirect:/categories";
    }
}