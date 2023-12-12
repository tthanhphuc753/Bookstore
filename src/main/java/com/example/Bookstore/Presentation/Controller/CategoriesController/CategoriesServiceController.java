package com.example.Bookstore.Presentation.Controller.CategoriesController;

import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
