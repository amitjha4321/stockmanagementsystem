package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Category;
import com.stockmanagementsystem.stockmanagementsystem.models.Sales;
import com.stockmanagementsystem.stockmanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/dashboard/admin/addcategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCategoryForm(Model model) {
        List<Category> categoriesList = categoryRepository.findAll();
        model.addAttribute("categories", categoriesList);
        model.addAttribute("category", new Category());
        return "/admin/addcategory";
    }

    @PostMapping(value = "/dashboard/admin/addcategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCategory(Category category,Model model) {
        categoryRepository.save(category);
        model.addAttribute("success", "Category Record is saved successfully!!!");
        model.addAttribute("category", new Category());
        return "admin/addcategory";
    }

    @GetMapping("/categorylist")
    public String getCategoryList(Model model){
        List<Category> categoryList=categoryRepository.findAll();
        model.addAttribute("category",new Category());
        model.addAttribute("categories",categoryList);
        return "/admin/categorieslist";

    }
}
