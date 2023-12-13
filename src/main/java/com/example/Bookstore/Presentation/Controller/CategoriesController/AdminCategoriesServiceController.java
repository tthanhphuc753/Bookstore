package com.example.Bookstore.Presentation.Controller.CategoriesController;

import com.example.Bookstore.Domain.CategoriesService.AdminCategoriesService;
import com.example.Bookstore.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceController {
    private final AdminCategoriesService adminCategoriesService;

    public List<Categories> getAllCategory() {
        //model.addAttribute("categories",categoriesService.getAllCategory());
        return adminCategoriesService.getAllCategory();
    }

    public void addCategories(Categories categories) {
        adminCategoriesService.addCategory(categories);
    }


}
