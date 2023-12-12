package com.example.Bookstore.Presentation.Controller.CategoriesController;

import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoriesServiceController {

    private final CategoriesService categoriesService;


    public List<Categories> getAllCategory() {
        //model.addAttribute("categories",categoriesService.getAllCategory());
        return categoriesService.getAllCategory();
    }

    public void addCategories(Categories categories) {
        categoriesService.addCategory(categories);
    }


}
