package com.example.SalesManagment.Controller.categoriesController;

import com.example.SalesManagment.Model.Product.Categories;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoriesController {

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
