package com.example.Bookstore.Presentation.Controller.BookController;

import com.example.Bookstore.Domain.BookService.ClientBookService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Presentation.Controller.CategoriesController.ClientCategoriesServiceController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class ClientBookController {
    private final ClientBookService clientBookService;
    private final ClientCategoriesServiceController clientCategoriesServiceController;


        public void pageSize(Model model, int page, int size) {

        Page<Book> booksPage = clientBookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("books", booksPage.getContent());
    }

    public List<Book> getAllBook() {
        return clientBookService.getAllBook();
    }

    public Optional<Book> findById(Long bookId) {
        return clientBookService.findById(bookId);
    }

    public List<Book> searchBooks(String keyword) {
        return clientBookService.searchBooks(keyword);
    }
}
