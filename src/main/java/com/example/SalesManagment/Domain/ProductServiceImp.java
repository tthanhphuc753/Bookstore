package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Persistence.DAO.ProductRepository;
import com.example.SalesManagment.Domain.Model.Book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct(Book book) {
        productRepository.save(book);
    }

    @Override
    public List<Book> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public long countProduct() {
        return productRepository.count();
    }

    @Override
    public void addCategories(long productID, long categoriesID)
    {

    }
}
