package com.example.Bookstore.Presentation.Controller.AdminController;


import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Book.Categories;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CategoriesRepository;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
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

    private final CategoriesServiceController categoriesController;
    private final BookController bookController;

    @GetMapping("/homepage")
    public String showAdminPage(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        bookController.pageSize(model, page, size);
        return "admin";
    }

    @GetMapping("book/update-form/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        bookController.showUpdateBookForm(model, id);
        return "updatebook";
    }

    @PostMapping("book/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book newBook) {
        bookController.updateBook(id, newBook);
        return "redirect:/admin/homepage";
    }

    @GetMapping("book/add-form")
    public String showAddForm(Model model) {
        bookController.showAddBookForm(model);
        return "addbook";
    }


    @PostMapping("book/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookController.addBook(book);
        return "redirect:/admin/homepage";
    }

    @PostMapping("/books/delete/{id}")
    public String removeBook(@PathVariable Long id) {
        bookController.deleteBook(id);
        return "redirect:/admin/homepage";
    }

    @PostMapping("category/add")
    public String addBook(@ModelAttribute("categories") Categories categories) {
        categoriesController.addCategories(categories);
        return "redirect:/category/list";
    }

    @GetMapping("category/list")
    public String getAllCategory(Model model)
    {
        model.addAttribute("categories",categoriesController.getAllCategory());
        return "categorylist";
    }

}
