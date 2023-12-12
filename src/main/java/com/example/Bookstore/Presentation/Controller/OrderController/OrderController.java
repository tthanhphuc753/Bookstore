package com.example.Bookstore.Presentation.Controller.OrderController;

import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Domain.OrderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }


    public void addOrder(Long cartId, Long userId) {
        orderService.addOrder(cartId, userId);
    }
    public void deleteOrder(Long orderId)
    {
        orderService.deleteOrderById(orderId);
    }



}
