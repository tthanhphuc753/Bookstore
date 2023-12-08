package com.example.Bookstore.Domain.CategoriesService;

import com.example.Bookstore.Domain.Model.Book.Categories;
import com.example.Bookstore.Persistence.DAO.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImp implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public void addCategory(Categories categories) {
        categoriesRepository.save(categories);
    }

    @Override
    public List<Categories> getAllCategory() {
        return categoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> findByName(String name) {
        return categoriesRepository.findByName(name);
    }

    @Override
    public long countCategory() {
        return categoriesRepository.count();
    }
}
