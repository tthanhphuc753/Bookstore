package com.example.SalesManagment.Persistence.DAO;

import com.example.SalesManagment.Domain.Model.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
}
