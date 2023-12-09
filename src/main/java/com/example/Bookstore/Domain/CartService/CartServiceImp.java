package com.example.Bookstore.Domain.CartService;

import com.example.Bookstore.Domain.Model.Cart.Cart;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final BookRepository bookRepository;
    private final CartRepository cartRepository;

    private final Map<Long, Cart> cartMap = new HashMap<>();


    @Override
    public void addToCart(Cart item) {
        Cart cartItem = cartMap.get(item.getBookID());
        if (cartItem == null) {
            cartMap.put(item.getBookID(), item);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cartRepository.save(cartItem);

    }

    @Override
    public void removeFromCart(long id) {
        cartMap.remove(id);
    }

    @Override
    public Cart updateCart(Long bookId, int quantity) {
        Cart cartItem = cartMap.get(bookId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }

    @Override
    public void clearAll() {
        cartMap.clear();
    }

    @Override
    public Collection<Cart> getAlls() {
        return cartMap.values();
    }
}
