package com.example.Bookstore.Presentation.Controller.AdminController;


import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Book.Categories;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class adminController {

    private final BookService bookService;
    private final CategoriesService categoriesService;
    @GetMapping("/homepage")
    public String showAdminPage(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        Page<Book> booksPage = bookService.getAllBooks(PageRequest.of(page, size));

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("books", booksPage.getContent());
        return "admin";
    }

    @GetMapping("book/update-form/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Book> bookOptional = bookService.findById(id);
        bookOptional.ifPresent(book -> model.addAttribute("book", book));
        return "updatebook";
    }

    @PostMapping("book/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book newBook) {
        bookService.updateBook(id, newBook);
        return "redirect:/admin/homepage";
    }

    @GetMapping("book/add-form")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("category",categoriesService.getAllCategory());
        return "addbook";
    }


    @PostMapping("book/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/admin/homepage";
    }

    @PostMapping("/books/delete/{id}")
    public String removeBook(@PathVariable Long id) {

        bookService.removeBook(id);
        return "redirect:/admin/homepage";
    }

    @PostMapping("category/add")
    public String addBook(@ModelAttribute("categories") Categories categories) {

        categoriesService.addCategory(categories);
        return "redirect:/category/list";
    }

}
