package com.davidgayer.poznavacka.controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.davidgayer.poznavacka.model.Category;
import com.davidgayer.poznavacka.model.PictureEntity;
import com.davidgayer.poznavacka.repository.CategoryRepository;
import com.davidgayer.poznavacka.repository.PictureEntityRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomepageController {

    private final CategoryRepository categoryRepository;
    private final PictureEntityRepository pictureEntityRepository;
    private final Random random = new Random();

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<PictureEntity> pictures = pictureEntityRepository.findAll();
        Collections.shuffle(pictures, random);
        model.addAttribute("categories", categories);
        model.addAttribute("pictures", pictures);
        return "homepage";
    }

    

}
