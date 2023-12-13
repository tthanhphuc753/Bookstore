package com.example.Bookstore.Domain.CategoriesService;

import com.example.Bookstore.Domain.Model.Book.Categories;

import java.util.List;

public interface AdminCategoriesService {
    void addCategory(Categories categories);

    List<Categories> getAllCategory();
}
