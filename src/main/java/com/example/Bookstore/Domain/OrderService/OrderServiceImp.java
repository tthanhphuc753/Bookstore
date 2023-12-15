package com.example.Bookstore.Domain.OrderService;

import com.example.Bookstore.Domain.CartService.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import com.example.Bookstore.Persistence.DAO.OrderdetailRepository;
import com.example.Bookstore.Persistence.DAO.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;


@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final CartRepository cartRepository;
    private final OrderdetailRepository orderdetailRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartService cartService;

    @Override
    public Order addOrder(Long userId, HttpSession session) {
        Map<Long, CartItem> cartMap = cartService.getCartMap(session);

        Set<Book> bookList = new HashSet<>();
        User user = userRepository.findById(userId).get();
        Order newOrder = new Order();

        for (Map.Entry<Long, CartItem> entry : cartMap.entrySet()) {
            Book book = bookRepository.findById(entry.getKey()).get();
            bookList.add(book);
        }
        newOrder.setDate(new Date());
        newOrder.setUser(user);
        newOrder.setBookList(bookList);

        return orderdetailRepository.save(newOrder);

    }

    public Collection<CartItem> getAlls(HttpSession session) {
        Map<Long, CartItem> cartMap = cartService.getCartMap(session);
        return cartMap.values();
    }

    @Override
    public void deleteOrderById(long orderId) {
        orderdetailRepository.deleteById(orderId);
    }


    @Override
    public List<Order> getAllOrder() {
        return orderdetailRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return Optional.empty();
    }
}
