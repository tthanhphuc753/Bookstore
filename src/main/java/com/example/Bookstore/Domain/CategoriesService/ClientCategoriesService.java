package com.example.Bookstore.Domain.CategoriesService;

import com.example.Bookstore.Domain.Model.Book.Categories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientCategoriesService {

    List<Categories> getAllCategory();


}
