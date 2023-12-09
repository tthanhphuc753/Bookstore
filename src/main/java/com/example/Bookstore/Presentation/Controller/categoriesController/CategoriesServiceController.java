package com.example.Bookstore.Presentation.Controller.categoriesController;

import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoriesServiceController {

    private final CategoriesService categoriesService;

    @GetMapping("list")
    public String getAllCategory(Model model) {
        model.addAttribute("categories", categoriesService.getAllCategory());
        return "categorylist";
    }




}
