package com.example.Bookstore.Presentation.Controller.CategoriesController;

import com.example.Bookstore.Domain.CategoriesService.ClientCategoriesService;
import com.example.Bookstore.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCategoriesServiceController {

    private final ClientCategoriesService clientCategoriesService;

    public List<Categories> getAllCategory() {
        //model.addAttribute("categories",categoriesService.getAllCategory());
        return clientCategoriesService.getAllCategory();
    }
}
