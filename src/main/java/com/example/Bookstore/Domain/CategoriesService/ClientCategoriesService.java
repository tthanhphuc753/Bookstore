package com.example.Bookstore.Domain.CategoriesService;

import com.example.Bookstore.Domain.Model.Book.Categories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientCategoriesService {

    List<Categories> getAllCategory();


}
