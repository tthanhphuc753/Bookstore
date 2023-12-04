package com.example.SalesManagment.Presentation.Controller.categoriesController;

import com.example.SalesManagment.Domain.CategoriesService;
import com.example.SalesManagment.Domain.Model.Book.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoriesServiceController {

    private final CategoriesService categoriesService;

    @GetMapping("list")
    public List<Categories> getAllCategory() {
        return categoriesService.getAllCategory();
    }

    @GetMapping("count")
    public Long countProduct() {
        return categoriesService.countCategory();
    }

    @PostMapping("add")
    public void addCategory(@RequestBody Categories categories) {
        categoriesService.addCategory(categories);
    }

}
