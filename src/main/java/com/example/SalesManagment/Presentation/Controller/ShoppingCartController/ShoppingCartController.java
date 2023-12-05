package com.example.SalesManagment.Presentation.Controller.ShoppingCartController;

import com.example.SalesManagment.Domain.CartService;
import com.example.SalesManagment.Domain.Model.Book.Book;
import com.example.SalesManagment.Domain.Model.Cart.Cart;
import com.example.SalesManagment.Persistence.DAO.BookRepository;
import com.example.SalesManagment.Persistence.DAO.CartRepository;
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
