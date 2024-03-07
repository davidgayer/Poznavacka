package com.davidgayer.poznavacka.controller;

import com.davidgayer.poznavacka.model.Category;
import com.davidgayer.poznavacka.model.PictureEntity;
import com.davidgayer.poznavacka.repository.CategoryRepository;
import com.davidgayer.poznavacka.repository.PictureEntityRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PictureEntityController {

    private final CategoryRepository categoryRepository;
    private final PictureEntityRepository pictureEntityRepository;

    @GetMapping("/picture/save")
    public String saveForm(Model model) {
        model.addAttribute("picture", new PictureEntity());
        return "save-new-picture";
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

    @PostMapping("/savePicture")
    public String savePicture(@ModelAttribute("picture") PictureEntity entity) {
        pictureEntityRepository.save(entity);
        return "redirect:/successPicture";
    }

    @GetMapping("/successPicture")
    public String successPicture() {
        return "success";
    }

}
