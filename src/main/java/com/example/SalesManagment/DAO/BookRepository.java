package com.example.SalesManagment.DAO;

import com.example.SalesManagment.Model.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
