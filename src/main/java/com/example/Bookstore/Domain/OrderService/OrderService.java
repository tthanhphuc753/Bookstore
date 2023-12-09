package com.example.Bookstore.Domain.OrderService;
import com.example.Bookstore.Domain.Model.Order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order addOrder(Long id);
    void deleteOrderById(long id);
    List<Order> getAllOrder();
    Optional<Order> findOrderById(Long id);
}
