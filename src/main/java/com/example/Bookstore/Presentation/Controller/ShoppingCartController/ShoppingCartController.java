package com.example.Bookstore.Presentation.Controller.ShoppingCartController;

import com.example.Bookstore.Domain.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.Cart;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    @GetMapping("add/{id}")
    public String addToCart(@PathVariable("id") Long id)
    {
        Book book = bookRepository.findById(id).get();
        if(book!=null)
        {
            Cart item = new Cart();
            item.setBookID(book.getId());
            item.setName(book.getName());
            item.setPrice(book.getPrice());
            item.setQuantity(1);
            cartService.addToCart(item);
            return "Success";
        }
        else
            return "Fail";
    }
    @GetMapping("list")
    public List<Cart> getAll()
    {
        return cartRepository.findAll();
    }

}
