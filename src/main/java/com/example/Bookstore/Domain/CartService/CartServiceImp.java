package com.example.Bookstore.Domain.CartService;

import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private static final String SESSION_KEY_CART = "CART";

    private Map<Long, CartItem> getCartMap(HttpSession session) {
        Map<Long, CartItem> cartMap = (Map<Long, CartItem>) session.getAttribute(SESSION_KEY_CART);
        if (cartMap == null) {
            cartMap = new HashMap<>();
            session.setAttribute(SESSION_KEY_CART, cartMap);
        }
        return cartMap;
    }

    @Override
    public void addToCart(CartItem item, HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);

        CartItem cartItem = cartMap.get(item.getBook().getId());
        if (cartItem == null) {
            cartMap.put(item.getBook().getId(), item);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        session.setAttribute(SESSION_KEY_CART, cartMap);
    }

    @Override
    public void removeFromCart(long bookId, HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);

        cartMap.remove(bookId);

        session.setAttribute(SESSION_KEY_CART, cartMap);
    }

    @Override
    public void updateCart(Long bookId, int quantity, HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);

        CartItem cartItem = cartMap.get(bookId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
        }

        session.setAttribute(SESSION_KEY_CART, cartMap);

    }

    @Override
    public void clearAll(HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);
        cartMap.clear();
        session.setAttribute(SESSION_KEY_CART, cartMap);
    }

    @Override
    public Collection<CartItem> getAlls(HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);
        return cartMap.values();
    }

    @Override
    public int getCountItem(HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);
        return cartMap.size();
    }

    public Double getSumItem(HttpSession session) {
        Map<Long, CartItem> cartMap = getCartMap(session);
        Double sum = 0.0;
        for (int i = 0; i < cartMap.size(); i++) {

        }

        return sum;

    }
}
