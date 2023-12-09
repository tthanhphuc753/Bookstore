package com.example.Bookstore.Presentation.Controller.ShoppingCartController;

import com.example.Bookstore.Domain.CartService.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    @PostMapping("add")
    public String addToCart(@ModelAttribute("bookID") Long id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent())
        {
            Book book = optionalBook.get();
            CartItem item = new CartItem();
            item.setBookID(book.getId());
            item.setName(book.getName());
            item.setPrice(book.getPrice());
            item.setQuantity(1);
            cartService.addToCart(item);
            return "Them thanh cong";
        }
        else
            return "Them that bai";
    }
    @GetMapping("list")
    public String getAll(Model model)
    {
        model.addAttribute("cartitem",cartRepository.findAll());
        return "redirect:/book/homepage";
    }

    @DeleteMapping("delete")
    public String deleteCart (@PathVariable Long cartId){
        cartService.removeFromCart(cartId);
        return "";
    }

    @PostMapping("update")
    public String updateCartQuantity(@ModelAttribute("cartId") Long cartId,
                                     @ModelAttribute("quantity") int quantity) {
        cartService.updateCart(cartId, quantity);
        return "";
    }
}
