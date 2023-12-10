package com.example.Bookstore.Presentation.Controller.OrderController;

import com.example.Bookstore.Domain.OrderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("list")
    public String getAllOrder(Model model) {
        model.addAttribute("orders", orderService.getAllOrder());
        return "orderlist";
    }

    @PostMapping("add/{cartid}/{userid}")
    public void addOrder(@PathVariable Long cartId, @PathVariable Long userId) {
        orderService.addOrder(cartId, userId);
    }


}
