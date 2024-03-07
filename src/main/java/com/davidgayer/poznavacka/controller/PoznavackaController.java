package com.davidgayer.poznavacka.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.davidgayer.poznavacka.model.Category;
import com.davidgayer.poznavacka.model.PictureEntity;
import com.davidgayer.poznavacka.repository.CategoryRepository;
import com.davidgayer.poznavacka.repository.PictureEntityRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PoznavackaController {

    private final PictureEntityRepository pictureEntityRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/category/{id}")
public String getPicturesOfCategory(Model model, @PathVariable Long id) {
    List<PictureEntity> categoryPictures = pictureEntityRepository.findAllByCategoryId(id);
    model.addAttribute("categoryPictures", categoryPictures);
    model.addAttribute("currentPictureIndex", 0);
    return "poznavacka-game";
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
}
