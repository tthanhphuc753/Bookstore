package com.example.Bookstore.Presentation.Controller.CategoriesController;

import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoriesServiceController {

    private final CategoriesService categoriesService;


    public List<Categories> getAllCategory() {
        //model.addAttribute("categories",categoriesService.getAllCategory());
        return categoriesService.getAllCategory();
    }

    public void addCategories(Categories categories)
    {
        categoriesService.addCategory(categories);
    }






}
