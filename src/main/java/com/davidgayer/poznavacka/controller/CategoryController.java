package com.davidgayer.poznavacka.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.davidgayer.poznavacka.model.Category;
import com.davidgayer.poznavacka.repository.CategoryRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/category/save")
    public String showForm(Model model) {
        model.addAttribute("category", new Category());
        return "save-new-category";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    Long categoryId = Long.parseLong(text);
                    Category category = categoryRepository.findById(categoryId).orElse(null);
                    setValue(category);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid category ID");
                }
            }
        });
    }

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@NonNull @ModelAttribute Category entity) {
        categoryRepository.save(entity);
        return "redirect:/successCategory";
    }

    @GetMapping("/successCategory")
    public String success() {
        return "Success";
    }

}
