package com.example.Bookstore.Domain.OrderService;
import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Domain.Model.User.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order addOrder(Long userId, HttpSession session);

    // Order addOrder(Long cartid, Long userId);
    void deleteOrderById(long orderId);
    List<Order> getAllOrder();
    Optional<Order> findOrderById(Long id);
}
